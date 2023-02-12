package get.a.big.head.newNRG.equipment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipmentDto {

    private String operationalName;
    private String ratedCurrent;
    private String ratedVoltage;
}
