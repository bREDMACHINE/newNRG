package get.a.big.head.newNRG.specificationvalue;

import get.a.big.head.newNRG.specifications.Specification;
import get.a.big.head.newNRG.types.Type;

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
                                                          Specification specification, Type type) {
        SpecificationValue specificationValue = new SpecificationValue();
        specificationValue.setSpecification(specification);
        specificationValue.setSpecificationValueId(specificationValueDto.getSpecificationValueId());
        specificationValue.setType(type);
        return specificationValue;
    }
}
