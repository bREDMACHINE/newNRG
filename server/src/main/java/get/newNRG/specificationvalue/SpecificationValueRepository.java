package get.newNRG.specificationvalue;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationValueRepository extends JpaRepository<SpecificationValue, Long> {

    List<SpecificationValue> findByTypeTypeId(Long id, Pageable pageable);
}
