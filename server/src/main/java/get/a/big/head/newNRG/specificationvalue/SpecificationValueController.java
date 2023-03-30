package get.a.big.head.newNRG.specificationvalue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class SpecificationValueController {

    private final SpecificationValueService specificationValueService;

    @PostMapping("/moderator/equipment/type/specification/value")
    public SpecificationValueDto addSpecificationValue(@RequestBody SpecificationValueDto specificationValueDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment/type/specification/value, specificationValue={}", specificationValueDto);
        SpecificationValueDto value = specificationValueService.addSpecificationValue(specificationValueDto);
        log.info("Результат запроса {}", value);
        return value;
    }

    @DeleteMapping("/moderator/equipment/type/specification/value/{id}")
    public void deleteSpecificationValue(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/specification/value/{}", id);
        specificationValueService.deleteSpecificationValue(id);
    }

    @GetMapping("/user/equipment/type/specification/value/{id}")
    public SpecificationValueDto getSpecificationValue(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/specification/value/{}", id);
        SpecificationValueDto value =  specificationValueService.getSpecificationValue(id);
        log.info("Результат запроса {}", value);
        return value;
    }

    @GetMapping("/user/equipment/type/{id}/specification/values")
    public List<SpecificationValueDto> findAllSpecificationValues(@PathVariable Long id,
                                                                  @RequestParam int from,
                                                                  @RequestParam int size) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/{}/specification/values с листа {}", id, from);
        List<SpecificationValueDto> list = specificationValueService.findAllSpecificationValues(id, from, size);
        log.info("Результат запроса {}", list);
        return list;
    }
}
