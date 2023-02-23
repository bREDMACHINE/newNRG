package get.a.big.head.newNRG.users.frames;

import get.a.big.head.newNRG.users.dtos.User;

public class AdminAccountFrame extends UserAccountFrame {

    public AdminAccountFrame(User user) {
        super(user);
        panelButtons.add(buttonDeleteUser);
    }
}
