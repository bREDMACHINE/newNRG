package get.a.big.head.newNRG.projectdocumentation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectDocumentationRepository extends JpaRepository<ProjectDocumentation, Long> {

    Optional<ProjectDocumentation> findByNameProjectDocumentation(String name);
}
