package get.newNRG.spares;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpareDto {

    private Long spareId;
    private String spareName;
    private String spareDescription;
    private String spareCode;
}
