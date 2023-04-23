package get.newNRG.spares;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpareRepository extends JpaRepository<Spare, Long> {

    Optional<Spare> findBySpareName(String spareName);

    List<Spare> findByTypesTypeId(Long typeId, Pageable pageable);
}
