package m.muku.task.db.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    private final String clientId = "az2C61iqGxia6GcaJ0ocRQVdmHlCSk";
    private final String clientSecret = "ppWuOhQ3e4RVSmhWUecWgbOdKJc1JU";
    private final String base64 = "YXoyQzYxaXFHeGlhNkdjYUowb2NSUVZkbUhsQ1NrOnBwV3VPaFEzZTRSVlNtaFdVZWNXZ2JPZEtKYzFKVQ==";
    private String token;
    private String refreshToken;
    private Long expireIn;
    private int webId = 2003622;

}
