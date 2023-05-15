package get.newNRG.factories;

import com.google.gson.JsonParser;
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
import java.io.StringReader;
import java.util.List;

@Component
@Slf4j
public class FactoryClient extends BaseClient {

    @Autowired
    public FactoryClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void addFactory(Frame frame, FactoryDto factory, String userId) {
        log.info("Add factory {}", factory);
        Object object = response(post("/moderator/equipment/type/factory",  userId, factory), frame);
        if (object != null) {
            FactoryDto factoryResponse = FactoryMapper.toFactoryDto(object);
            frame.dispose();
            JOptionPane.showMessageDialog(frame,
                    "Наименование завода " + factoryResponse.getFactoryName() + " успешно добавлено");
        }
    }

    public void deleteFactory(Frame frame, Long factoryId, String userId) {
        log.info("Delete factory {}", factoryId);
        Object object = response(delete("/moderator/equipment/type/factory/" + factoryId,  userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Завод " + name + " удален");
        }
    }

    public List<FactoryDto> findAllFactories(Frame frame, String userId) {
        log.info("Find all factories");
        Object object = response(
                get("/user/equipment/type/factories",  userId),
                frame
        );
        if (object != null) {
            return FactoryMapper.toFactoryDtos(object);
        }
        return null;
    }

    private <T> T response(ResponseEntity<T> response, Frame frame) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            log.info("Result {}",  response.getBody().toString());
            return response.getBody();
        } else {
            if (frame != null) {
                JOptionPane.showMessageDialog(
                        frame,
                        response.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        return null;
    }
}
