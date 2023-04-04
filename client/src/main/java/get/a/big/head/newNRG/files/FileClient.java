package get.a.big.head.newNRG.files;

import get.a.big.head.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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

    public MultipartFile getFile(Frame frame, Long fileId, String userId) {
        ResponseEntity<Object> fileResponse = post("/user/file/" + fileId,  userId);
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
