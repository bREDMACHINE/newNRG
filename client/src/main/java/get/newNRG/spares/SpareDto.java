package get.newNRG.spares;

import get.newNRG.general.WithOpenCard;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SpareDto extends WithOpenCard {

    private String spareName;
    private String spareDescription;
    private String spareCode;
}
