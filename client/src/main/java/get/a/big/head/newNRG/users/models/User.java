package get.a.big.head.newNRG.users.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    private String email;
    private String userId;
    private String role;
    private String status;
}
