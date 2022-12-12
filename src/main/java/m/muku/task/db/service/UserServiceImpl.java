package m.muku.task.db.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import m.muku.task.db.entity.Partner;
import m.muku.task.db.model.Client;
import m.muku.task.db.model.Results;
import m.muku.task.db.repository.PartnerRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Client client = new Client();
    private final PartnerRepository partnerRepository;


    @PostConstruct
    @Override
    public void getToken() throws IOException {
        URL url = new URL("https://api.admitad.com/token/");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Authorization", "Basic "+this.client.getBase64());
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("grant_type=client_credentials&client_id="+this.client.getClientId()+"&scope=advcampaigns websites public_data advcampaigns_for_website");
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        this.client.setToken(jsonObject.get("access_token").getAsString());
        this.client.setRefreshToken(jsonObject.get("refresh_token").getAsString());
        this.client.setExpireIn(jsonObject.get("expires_in").getAsLong());
        System.out.println(this.client.getToken());
        refreshToken();
    }
    @Scheduled(fixedDelay =604800 * 1000L)
    @Override
    public void refreshToken() throws IOException{
        URL url = new URL("https://api.admitad.com/token/");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("grant_type=refresh_token&client_id="+this.client.getClientId()+"&refresh_token="+this.client.getRefreshToken()+"&client_secret"+ client.getClientSecret());
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        this.client.setToken(jsonObject.get("access_token").getAsString());
        this.client.setRefreshToken(jsonObject.get("refresh_token").getAsString());
        this.client.setExpireIn(jsonObject.get("expires_in").getAsLong());
    }
    @Scheduled(fixedDelay = 10000)
    @Override
    public void saveAndUpdatePartners() throws IOException{
        URL url = new URL("https://api.admitad.com/advcampaigns/?limit=2");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("Authorization", "Bearer "+this.client.getToken());

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
        Gson gson = new Gson();
       Results results= gson.fromJson(response, Results.class);
        for (Partner partner : results.getResults()) {
            partnerRepository.save(partner);
        }
    }
}
