package get.a.big.head.newNRG.users.models;

public enum Permission {
    READ("READ"),
    WRITE("WRITE"),
    GOD("GOD");

    private final String permission;


    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
