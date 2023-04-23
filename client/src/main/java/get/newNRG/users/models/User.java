package get.newNRG.users.models;

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
