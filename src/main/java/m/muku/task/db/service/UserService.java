package m.muku.task.db.service;

import java.io.IOException;
import java.net.MalformedURLException;

public interface UserService {
    void getToken() throws MalformedURLException, IOException;
    void refreshToken() throws IOException;
    void saveAndUpdatePartners() throws IOException;
}
