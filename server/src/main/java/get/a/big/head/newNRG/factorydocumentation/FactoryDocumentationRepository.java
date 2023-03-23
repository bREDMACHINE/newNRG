package get.a.big.head.newNRG.factorydocumentation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FactoryDocumentationRepository extends JpaRepository<FactoryDocumentation, Long> {

    Optional<FactoryDocumentation> findByNameFactoryDocumentation(String nameFactoryDocumentation);

    List<FactoryDocumentation> findAll();
}
