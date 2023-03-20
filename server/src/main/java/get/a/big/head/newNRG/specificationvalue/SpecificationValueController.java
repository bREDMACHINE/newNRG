package get.a.big.head.newNRG.specificationvalue;

import get.a.big.head.newNRG.specifications.SpecificationDto;
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
        return specificationValueService.addSpecificationValue(specificationValueDto);
    }

    @DeleteMapping("/moderator/equipment/type/specification/value/{id}")
    public void deleteSpecificationValue(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/specification/value/{}", id);
        specificationValueService.deleteSpecificationValue(id);
    }

    @GetMapping("/user/equipment/type/specification/value/{id}")
    public SpecificationValueDto getSpecificationValue(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/specification/value/{}", id);
        return specificationValueService.getSpecificationValue(id);
    }

    @GetMapping("/user/equipment/type/{id}/specification/values")
    public List<SpecificationValueDto> findAllSpecificationValues(@PathVariable Long id,
                                                                  @RequestParam int from,
                                                                  @RequestParam int size) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/{}/specification/values", id);
        return specificationValueService.findAllSpecificationValues(id, from, size);
    }
}
