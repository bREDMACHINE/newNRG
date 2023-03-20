package get.a.big.head.newNRG.specificationvalue;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationValueRepository extends JpaRepository<SpecificationValue, Long> {

    List<SpecificationValueDto> findByTypeTypeId(Long id);
}
