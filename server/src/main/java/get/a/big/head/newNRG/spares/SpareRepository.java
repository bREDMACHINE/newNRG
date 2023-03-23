package get.a.big.head.newNRG.spares;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpareRepository extends JpaRepository<Spare, Long> {

    Optional<Spare> findBySpareName(String spareName);
}
