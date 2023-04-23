package get.newNRG.users.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFullDto {

    private String email;
    private String role;
    private String status;
}
