package get.newNRG.types;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TypeShortDto {

    private Long typeId;
    private String typeName;
    private Long factoryId;
    private List<Long> specificationValues;
}
