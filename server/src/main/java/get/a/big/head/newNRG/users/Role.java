package get.a.big.head.newNRG.users;

import java.util.Optional;

public enum Role {
    USER, ADMIN;

    public static Optional<Role> from(String roleString) {
        for (Role role : values()) {
            if (role.name().equalsIgnoreCase(roleString)) {
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }
}
