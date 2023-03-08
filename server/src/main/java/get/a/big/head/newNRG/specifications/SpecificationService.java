package get.a.big.head.newNRG.specifications;

import java.util.List;

public interface SpecificationService {

    SpecificationDto addSpecification(SpecificationDto specificationDto);
    void deleteSpecification(Long id);
    SpecificationDto getSpecification(Long id);
    List<SpecificationDto> findAllSpecifications();
}
