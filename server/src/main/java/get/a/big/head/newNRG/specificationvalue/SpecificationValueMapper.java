package get.a.big.head.newNRG.specificationvalue;

import java.util.List;

public class SpecificationValueMapper {

    public static SpecificationValueDto toSpecificationValueDto(SpecificationValue specificationValue) {
        return SpecificationValueDto.builder()
                .specificationValueId(specificationValue.getSpecificationValueId())
                .specification(new SpecificationValueDto.SpecificationDtoForSpecificationValueDto(
                                specificationValue.getSpecification().getSpecificationId(),
                                specificationValue.getSpecification().getSpecificationName()))
                .typeId(specificationValue.getType().getTypeId())
                .specificationValue(specificationValue.getSpecificationValue())
                .build();
    }

    public static SpecificationValue toSpecificationValue(SpecificationValueDto specificationValueDto) {
        SpecificationValue specificationValue = new SpecificationValue();
        specificationValue.setSpecification();
    }

    public static List<SpecificationValueDto> toSpecidicationValueDtos(SpecificationValueDto specificationValueDto) {
        return ;
    }
}
