package get.newNRG.types;

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

    @PostMapping("/moderator/equipment/type")
    public TypeShortDto addType(@RequestBody TypeShortDto typeShortDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment/type, type={}", typeShortDto);
        return typeService.addType(typeShortDto);
    }

    @PatchMapping("/moderator/equipment/type")
    public TypeDto updateType(@RequestBody TypeDto typeDto) {
        log.info("Получен Patch запрос к эндпоинту /moderator/equipment/type, type={}", typeDto);
        return typeService.updateType(typeDto);
    }

    @DeleteMapping("/moderator/equipment/type/{id}")
    public void deleteType(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/{}", id);
        typeService.deleteType(id);
    }

    @GetMapping("/user/equipment/type/{id}")
    public TypeDto getType(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/{}", id);
        return typeService.getType(id);
    }

    @GetMapping("/user/equipment/types")
    public List<TypeShortDto> findAllTypes() {
        log.info("Получен Get запрос к эндпоинту /user/equipment/types");
        return typeService.findAllTypes();
    }
}
