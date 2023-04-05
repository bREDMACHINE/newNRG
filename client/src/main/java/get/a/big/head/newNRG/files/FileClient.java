package get.a.big.head.newNRG.files;

import get.a.big.head.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.swing.*;
import java.awt.*;

@Service
@Slf4j
public class FileClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public FileClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public DataFile addFile(Frame frame, DataFile dataFile, String userId) {
        log.info("Add file {}",  dataFile);
        ResponseEntity<Object> fileResponse = post("/user/file/", userId, dataFile);
        if (fileResponse.getStatusCode().is2xxSuccessful() && fileResponse.getBody() != null) {
            log.info("Result {}",  fileResponse.getBody().toString());
            return FileMapper.toDataFile(fileResponse.getBody());
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

    public DataFile getFile(Frame frame, Long fileId, String userId) {
        log.info("Get file {}",  fileId);
        ResponseEntity<Object> getFileResponse = get("/user/file/" + fileId,  userId);
        if (getFileResponse.getStatusCode().is2xxSuccessful() && getFileResponse.getBody() != null) {
            log.info("Result {}",  getFileResponse.getBody().toString());
            return FileMapper.toDataFile(getFileResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    getFileResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
}
