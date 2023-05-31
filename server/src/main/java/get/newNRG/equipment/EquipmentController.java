package get.newNRG.equipment;

import get.newNRG.equipment.dtos.EquipmentDto;
import get.newNRG.equipment.dtos.EquipmentShortDto;
import get.newNRG.equipment.services.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping("/moderator/equipment")
    public EquipmentShortDto addEquipment(@RequestBody EquipmentShortDto equipmentShortDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment, equipment={}", equipmentShortDto);
        EquipmentShortDto equipment = equipmentService.addEquipment(equipmentShortDto);
        log.info("Результат запроса {}", equipment);
        return equipment;
    }

    @PatchMapping("/moderator/equipment")
    public EquipmentDto updateEquipment(@RequestBody EquipmentDto equipmentDto) {
        log.info("Получен Patch запрос к эндпоинту /moderator/equipment, equipment={}", equipmentDto);
        EquipmentDto equipment = equipmentService.updateEquipment(equipmentDto);
        log.info("Результат запроса {}", equipment);
        return equipment;
    }

    @DeleteMapping("/moderator/equipment/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/{}", id);
        equipmentService.deleteEquipment(id);
    }

    @GetMapping("/user/equipment")
    public EquipmentDto getEquipmentByName(@RequestParam String text) {
        log.info("Получен Get запрос к эндпоинту /user/equipment text={}", text);
        EquipmentDto equipment = equipmentService.getEquipmentByName(text);
        log.info("Результат запроса {}", equipment);
        return equipment;
    }

    @GetMapping("/user/equipment/{id}")
    public EquipmentDto getEquipmentById(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/{}", id);
        EquipmentDto equipment = equipmentService.getEquipmentById(id);
        log.info("Результат запроса {}", equipment);
        return equipment;
    }
}
