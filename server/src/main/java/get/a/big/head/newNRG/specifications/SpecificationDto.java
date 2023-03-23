package get.a.big.head.newNRG.specifications;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpecificationDto {

    private Long specificationId;
    private String specificationName;
    private String specificationDescription;
}
