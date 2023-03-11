package get.a.big.head.newNRG.spares;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class SpareController {

    private final SpareService spareService;

    @PostMapping("/moderator/equipment/type/spare")
    public SpareDto addSpare(@RequestBody SpareDto spareDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment/type/spare, spare={}", spareDto);
        return spareService.addSpare(spareDto);
    }

    @DeleteMapping("/moderator/equipment/type/spare/{id}")
    public void deleteSpare(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/spare/{}", id);
        spareService.deleteSpare(id);
    }

    @GetMapping("/moderator/equipment/type/spare/{id}")
    public SpareDto getSpare(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /moderator/equipment/type/spare/{}", id);
        return spareService.getSpare(id);
    }

    @GetMapping("/user/equipment/type/{id}/spare")
    public List<SpareDto> findAllSpares(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/{}/spare", id);
        return spareService.findAllSpares(id);
    }
}
