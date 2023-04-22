package get.newNRG.files;

import get.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.swing.*;
import java.awt.*;

@Component
@Slf4j
public class DataFileClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public DataFileClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public DataFileDto addFile(Frame frame, DataFileDto dataFile, String userId) {
        log.info("Add file {}",  dataFile);
        ResponseEntity<Object> fileAddResponse = post("/user/file/", userId, dataFile);
        return response(fileAddResponse, frame);
    }

    public DataFileDto getFile(Frame frame, Long fileId, String userId) {
        log.info("Get file {}",  fileId);
        ResponseEntity<Object> getFileResponse = get("/user/file/" + fileId,  userId);
        return response(getFileResponse, frame);
    }

    private DataFileDto response(ResponseEntity<Object> response, Frame frame) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            log.info("Result {}",  response.getBody().toString());
            return DataFileMapper.toDataFileDto(response.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    response.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
}
