package get.a.big.head.newNRG.users;

public enum Permission {
    ENGINEERS_READ("engineers:read"),
    ENGINEERS_WRITE("engineers:write");

    private final String permission;


    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
