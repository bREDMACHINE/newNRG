package get.a.big.head.newNRG.factorydocumentation;

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
        return factoryDocumentationService.addDocument(factoryDocumentationDto);
    }

    @DeleteMapping("/moderator/equipment/type/document/{id}")
    public void deleteDocument(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/document/{}", id);
        factoryDocumentationService.deleteDocument(id);
    }

    @GetMapping("/user/equipment/type/document/{id}")
    public FactoryDocumentationDto getDocument(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/document/{}", id);
        return factoryDocumentationService.getDocument(id);
    }

    @GetMapping("/user/equipment/type/{id}/documents")
    public List<FactoryDocumentationDto> findAllDocuments(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/{}/documents", id);
        return factoryDocumentationService.findAllDocuments(id);
    }
}
