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

    @PostMapping("/equipment/moderator/type/spare")
    public SpareDto addSpare(@RequestBody SpareDto spareDto) {
        log.info("Получен Post запрос к эндпоинту /equipment/moderator/type/spare, spare={}", spareDto);
        return spareService.addSpare(spareDto);
    }

    @DeleteMapping("/equipment/moderator/type/spare/{id}")
    public void deleteSpare(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /equipment/moderator/type/spare/{}", id);
        spareService.deleteSpare(id);
    }

    @GetMapping("/equipment/moderator/type/spare/{id}")
    public SpareDto getSpare(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /equipment/moderator/type/spare/{}", id);
        return spareService.getSpare(id);
    }

    @GetMapping("/equipment/type/{id}/spare")
    public List<SpareDto> findAllSpares(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /equipment/type/{}/spare", id);
        return spareService.findAllSpares(id);
    }
}
