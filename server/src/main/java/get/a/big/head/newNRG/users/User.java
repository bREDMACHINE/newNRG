package get.a.big.head.newNRG.users;

import lombok.*;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_password")
    private String password;
    private String email;
    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
