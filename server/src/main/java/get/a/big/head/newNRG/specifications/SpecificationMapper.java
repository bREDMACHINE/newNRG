package get.a.big.head.newNRG.specifications;

public class SpecificationMapper {

    public static Specification toSpecification(SpecificationDto specificationDto) {
        Specification specification = new Specification();
        specification.setSpecificationName(specificationDto.getSpecificationName());
        specification.setSpecificationDescription(specificationDto.getSpecificationDescription());
        specification.setSpecificationValue(specificationDto.getSpecificationValue());
        return specification;
    }

    public static SpecificationDto toSpecificationDto(Specification specification) {
        return SpecificationDto.builder()
                .specificationId(specification.getSpecificationId())
                .specificationName(specification.getSpecificationName())
                .specificationDescription(specification.getSpecificationDescription())
                .specificationValue(specification.getSpecificationValue())
                .build();
    }
}
