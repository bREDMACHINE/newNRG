package get.newNRG.specifications;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    Optional<Specification> findBySpecificationName(String specificationName);
}
