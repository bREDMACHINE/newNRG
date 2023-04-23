package get.newNRG.factories;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FactoryDto {

    private Long factoryId;
    private String factoryName;
}
