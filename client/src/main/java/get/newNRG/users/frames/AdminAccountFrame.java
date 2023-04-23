package get.newNRG.users.frames;

import get.newNRG.users.models.User;

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
