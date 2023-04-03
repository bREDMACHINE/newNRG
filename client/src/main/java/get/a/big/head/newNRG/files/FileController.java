package get.a.big.head.newNRG.files;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.awt.*;

@Controller
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FileController {

    private final FileClient fileClient;

    public MultipartFile getEquipment(Frame frame, Long fileId, String userId) {
        ResponseEntity<Object> fileResponse = fileClient.getFile(fileId, userId);
        if (fileResponse.getStatusCode().is2xxSuccessful() && fileResponse.getBody() != null) {
            return FileMapper.toMultipart(fileResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    fileResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
}
