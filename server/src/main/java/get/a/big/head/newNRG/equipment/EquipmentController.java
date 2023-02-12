package get.a.big.head.newNRG.equipment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping("/equipment")
    public EquipmentDto addEquipment(@RequestBody EquipmentDto equipmentDto) {
        log.info("Получен Post запрос к эндпоинту /equipment, equipment={}", equipmentDto);
        return equipmentService.addEquipment(equipmentDto);
    }

    @GetMapping("/equipment")
    public EquipmentDto getEquipment(@RequestParam String text) {
        log.info("Получен Get запрос к эндпоинту /equipment text={}", text);
        return equipmentService.getEquipment(text);
    }
}
