package get.a.big.head.newNRG.general;

import lombok.Data;

import javax.swing.*;

@Data
public class AdminMainFrame extends ModeratorMainFrame {

    private JButton userManager;

    public AdminMainFrame() {

        userManager = new JButton("Управление пользователями");
        panelButtons.add(userManager);
    }
}
