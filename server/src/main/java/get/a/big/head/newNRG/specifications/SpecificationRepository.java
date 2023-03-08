package get.a.big.head.newNRG.specifications;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    Optional<Specification> findBySpecificationName(String specificationName);
}
