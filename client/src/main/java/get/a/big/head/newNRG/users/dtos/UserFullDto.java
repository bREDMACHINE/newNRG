package get.a.big.head.newNRG.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFullDto {

    private String email;
    private String role;
    private String status;
}
