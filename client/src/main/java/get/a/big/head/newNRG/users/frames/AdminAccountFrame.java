package get.a.big.head.newNRG.users.frames;

import get.a.big.head.newNRG.users.dtos.User;

public class AdminAccountFrame extends UserAccountFrame {

    public AdminAccountFrame(User user) {
        super(user);
        panelButtons.remove(buttonRequestRole);
        panelButtons.remove(buttonClose);
        panelButtons.add(buttonDeleteUser);
        if (user.getStatus().equalsIgnoreCase("requested")) {
            panelButtons.add(buttonAcceptRole);
            panelButtons.add(buttonRejectRole);
        }
        panelButtons.add(buttonClose);
    }
}