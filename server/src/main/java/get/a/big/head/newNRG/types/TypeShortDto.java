package get.a.big.head.newNRG.types;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TypeShortDto {

    private Long typeId;
    private String typeName;
    private Long factoryId;
}
