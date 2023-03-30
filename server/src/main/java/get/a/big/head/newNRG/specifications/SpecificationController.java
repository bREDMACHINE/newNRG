package get.a.big.head.newNRG.specifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class SpecificationController {

    private final SpecificationService specificationService;

    @PostMapping("/moderator/equipment/type/specification")
    public SpecificationDto addSpecification(@RequestBody SpecificationDto specificationDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment/type/specification, specification={}", specificationDto);
        SpecificationDto specification = specificationService.addSpecification(specificationDto);
        log.info("Результат запроса {}", specification);
        return specification;
    }

    @DeleteMapping("/moderator/equipment/type/specification/{id}")
    public void deleteSpecification(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/specification/{}", id);
        specificationService.deleteSpecification(id);
    }

    @GetMapping("/user/equipment/type/specification/{id}")
    public SpecificationDto getSpecification(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/specification/{}", id);
        SpecificationDto specification = specificationService.getSpecification(id);
        log.info("Результат запроса {}", specification);
        return specification;
    }

    @GetMapping("/user/equipment/type/specifications")
    public List<SpecificationDto> findAllSpecifications() {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/specifications");
        List<SpecificationDto> list = specificationService.findAllSpecifications();
        log.info("Результат запроса {}", list);
        return list;
    }
}
