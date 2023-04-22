package get.newNRG.spares;

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
        SpareDto spare = spareService.addSpare(spareDto);
        log.info("Результат запроса {}", spare);
        return spare;
    }

    @DeleteMapping("/moderator/equipment/type/spare/{id}")
    public void deleteSpare(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/type/spare/{}", id);
        spareService.deleteSpare(id);
    }

    @GetMapping("/moderator/equipment/type/spare/{id}")
    public SpareDto getSpare(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /moderator/equipment/type/spare/{}", id);
        SpareDto spare = spareService.getSpare(id);
        log.info("Результат запроса {}", spare);
        return spare;
    }

    @GetMapping("/user/equipment/type/{id}/spares")
    public List<SpareDto> findAllSpares(@PathVariable Long id,
                                        @RequestParam int from,
                                        @RequestParam int size) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/type/{}/spares с листа {}", id, from);
        List<SpareDto> list = spareService.findAllSpares(id, from, size);
        log.info("Результат запроса {}", list);
        return list;
    }
}
