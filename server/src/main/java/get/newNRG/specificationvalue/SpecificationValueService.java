package get.newNRG.specificationvalue;

import java.util.List;

public interface SpecificationValueService {

    SpecificationValueDto addSpecificationValue(SpecificationValueDto specificationValueDto);
    void deleteSpecificationValue(Long id);
    SpecificationValueDto getSpecificationValue(Long id);
    List<SpecificationValueDto> findAllSpecificationValues(Long id, int from, int size);
}
