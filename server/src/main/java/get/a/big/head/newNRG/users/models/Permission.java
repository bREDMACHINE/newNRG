package get.a.big.head.newNRG.users.models;

public enum Permission {
    READ("read"),
    WRITE("write");

    private final String permission;


    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
