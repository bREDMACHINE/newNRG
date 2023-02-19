package get.a.big.head.newNRG.equipment;

import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Override
    public EquipmentDto addEquipment(EquipmentDto equipmentDto) {
        Equipment equipment = new Equipment();
        equipment.setOperationalName(equipmentDto.getOperationalName());
        equipment.setRatedCurrent(equipmentDto.getRatedCurrent());
        equipment.setRatedVoltage(equipmentDto.getRatedVoltage());
        return EquipmentMapper.toEquipmentDto(equipmentRepository.save(equipment));
    }

    @Override
    public EquipmentDto getEquipment(String text) {
        return EquipmentMapper.toEquipmentDto(equipmentRepository.findByOperationalName(text)
                .orElseThrow(() -> new NotFoundException("Указанный userId не существует")));
    }
}
