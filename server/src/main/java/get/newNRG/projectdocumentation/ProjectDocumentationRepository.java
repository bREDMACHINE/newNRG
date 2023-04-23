package get.newNRG.projectdocumentation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectDocumentationRepository extends JpaRepository<ProjectDocumentation, Long> {

    Optional<ProjectDocumentation> findByNameProjectDocumentation(String name);
    List<ProjectDocumentation> findByEquipmentEquipmentId(Long equipmentId, Pageable pageable);
}
