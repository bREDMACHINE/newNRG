package get.a.big.head.newNRG.equipment;

import get.a.big.head.newNRG.equipment.dtos.EquipmentDto;
import get.a.big.head.newNRG.equipment.dtos.EquipmentShortDto;
import get.a.big.head.newNRG.equipment.services.EquipmentService;
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
        return equipmentService.addEquipment(equipmentShortDto);
    }

    @PatchMapping("/moderator/equipment")
    public EquipmentDto updateEquipment(@RequestBody EquipmentDto equipmentDto) {
        log.info("Получен Patch запрос к эндпоинту /moderator/equipment, equipment={}", equipmentDto);
        return equipmentService.updateEquipment(equipmentDto);
    }

    @DeleteMapping("/moderator/equipment/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/{}", id);
        equipmentService.deleteEquipment(id);
    }

    @GetMapping("/user/equipment")
    public EquipmentDto getEquipment(@RequestParam String text) {
        log.info("Получен Get запрос к эндпоинту /user/equipment text={}", text);
        return equipmentService.getEquipment(text);
    }
}
