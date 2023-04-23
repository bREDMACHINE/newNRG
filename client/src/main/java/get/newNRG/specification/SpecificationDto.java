package get.newNRG.specification;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SpecificationDto {

    private Long specificationId;
    private String specificationName;
    private String specificationDescription;
}
