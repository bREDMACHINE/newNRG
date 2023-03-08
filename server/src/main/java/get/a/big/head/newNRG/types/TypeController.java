package get.a.big.head.newNRG.types;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class TypeController {

    private final TypeService typeService;

    @PostMapping("/equipment/moderator/type")
    public TypeShortDto addType(@RequestBody TypeShortDto typeShortDto) {
        log.info("Получен Post запрос к эндпоинту /equipment/moderator/type, type={}", typeShortDto);
        return typeService.addType(typeShortDto);
    }

    @PatchMapping("/equipment/moderator/type")
    public TypeDto updateType(@RequestBody TypeDto typeDto) {
        log.info("Получен Patch запрос к эндпоинту /equipment/moderator/type, type={}", typeDto);
        return typeService.updateType(typeDto);
    }

    @DeleteMapping("/equipment/moderator/type/{id}")
    public void deleteType(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /equipment/moderator/type/{}", id);
        typeService.deleteType(id);
    }

    @GetMapping("/equipment/type/{id}")
    public TypeDto getType(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /equipment/type/{}", id);
        return typeService.getType(id);
    }

    @GetMapping("/equipment/types")
    public List<TypeShortDto> findAllTypes() {
        log.info("Получен Get запрос к эндпоинту /equipment/types");
        return typeService.findAllTypes();
    }
}
