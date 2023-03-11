package get.a.big.head.newNRG.factories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class FactoryController {

    private final FactoryService factoryService;

    @PostMapping("/moderator/equipment/type/factory")
    public FactoryDto addFactory(@RequestBody FactoryDto factoryDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment/type/factory, factory={}", factoryDto);
        return factoryService.addFactory(factoryDto);
    }

    @DeleteMapping("/moderator/equipment/type/factory/{id}")
    public void deleteFactory(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/factory/{}", id);
        factoryService.deleteFactory(id);
    }

    @GetMapping("/user/equipment/type/factory/{id}")
    public FactoryDto getFactory(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/factory/{}", id);
        return factoryService.getFactory(id);
    }

    @GetMapping("/user/equipment/type/factories")
    public List<FactoryDto> findAllFactories() {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/factory");
        return factoryService.findAllFactories();
    }
}
