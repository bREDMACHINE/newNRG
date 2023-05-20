package get.newNRG.specificationvalue;

import get.newNRG.specifications.Specification;

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

    public static SpecificationValue toSpecificationValue(SpecificationValueDto specificationValueDto,
                                                          Specification specification) {
        SpecificationValue specificationValue = new SpecificationValue();
        specificationValue.setSpecification(specification);
        specificationValue.setSpecificationValueId(specificationValueDto.getSpecificationValueId());
        return specificationValue;
    }
}
