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

    @PostMapping("/equipment/moderator")
    public EquipmentShortDto addEquipment(@RequestBody EquipmentShortDto equipmentShortDto) {
        log.info("Получен Post запрос к эндпоинту /equipment/moderator, equipment={}", equipmentShortDto);
        return equipmentService.addEquipment(equipmentShortDto);
    }

    @PatchMapping("/equipment/moderator")
    public EquipmentDto updateEquipment(@RequestBody EquipmentDto equipmentDto) {
        log.info("Получен Patch запрос к эндпоинту /equipment/moderator, equipment={}", equipmentDto);
        return equipmentService.updateEquipment(equipmentDto);
    }

    @DeleteMapping("/equipment/moderator/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /equipment/moderator/{}", id);
        equipmentService.deleteEquipment(id);
    }

    @GetMapping("/equipment")
    public EquipmentDto getEquipment(@RequestParam String text) {
        log.info("Получен Get запрос к эндпоинту /equipment text={}", text);
        return equipmentService.getEquipment(text);
    }
}
