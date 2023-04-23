package get.newNRG.factorydocumentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class FactoryDocumentationController {

    private final FactoryDocumentationService factoryDocumentationService;

    @PostMapping("/moderator/equipment/type/document")
    public FactoryDocumentationDto addDocument(@RequestBody FactoryDocumentationDto factoryDocumentationDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment/type/document, document={}", factoryDocumentationDto);
        FactoryDocumentationDto document = factoryDocumentationService.addDocument(factoryDocumentationDto);
        log.info("Результат запроса {}", document);
        return document;
    }

    @DeleteMapping("/moderator/equipment/type/document/{id}")
    public void deleteDocument(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/document/{}", id);
        factoryDocumentationService.deleteDocument(id);
    }

    @GetMapping("/user/equipment/type/document/{id}")
    public FactoryDocumentationDto getDocument(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/document/{}", id);
        FactoryDocumentationDto document = factoryDocumentationService.getDocument(id);
        log.info("Результат запроса {}", document);
        return document;
    }

    @GetMapping("/user/equipment/type/{id}/documents")
    public List<FactoryDocumentationDto> findAllDocuments(@PathVariable Long id,
                                                          @RequestParam int from,
                                                          @RequestParam int size) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/{}/documents с листа {}", id, from);
        List<FactoryDocumentationDto> list = factoryDocumentationService.findAllDocuments(id, from, size);
        log.info("Результат запроса {}", list);
        return list;
    }
}
