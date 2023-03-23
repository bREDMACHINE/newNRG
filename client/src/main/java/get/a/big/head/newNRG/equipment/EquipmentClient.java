package get.a.big.head.newNRG.equipment;

import get.a.big.head.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Slf4j
public class EquipmentClient extends BaseClient {
    private static final String API_PREFIX = "";

    @Autowired
    public EquipmentClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addEquipment(EquipmentShortDto equipment, String userId) {
        return post("/moderator/equipment", userId, equipment);
    }

    public ResponseEntity<Object> updateEquipment(EquipmentDto equipment, String userId) {
        return patch("/moderator/equipment", userId, equipment);
    }

    public ResponseEntity<Object> deleteEquipment(Long equipmentId, String userId) {
        return delete("/moderator/equipment/" + equipmentId, userId);
    }

    public ResponseEntity<Object> getEquipment(String text, String userId) {
        return get("/user/equipment?text=" + text, userId);
    }
}
