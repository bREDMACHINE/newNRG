package get.a.big.head.newNRG.specificationvalue;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SpecificationValueDto {

    private Long specificationValueId;
    private SpecificationDtoForSpecificationValueDto specification;
    private Long typeId;
    private Long specificationValue;

    @Builder
    @Getter
    public static class SpecificationDtoForSpecificationValueDto {
        private Long specificationId;
        private String specificationName;
    }
}
