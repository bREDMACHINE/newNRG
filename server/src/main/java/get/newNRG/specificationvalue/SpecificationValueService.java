package get.newNRG.specificationvalue;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpecificationValueService {

    SpecificationValueDto addSpecificationValue(SpecificationValueDto specificationValueDto);
    ResponseEntity<?> deleteSpecificationValue(Long id);
    SpecificationValueDto getSpecificationValue(Long id);
    List<SpecificationValueDto> findAllSpecificationValues(Long id);
}
