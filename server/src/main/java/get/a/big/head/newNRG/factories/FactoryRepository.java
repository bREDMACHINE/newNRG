package get.a.big.head.newNRG.factories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FactoryRepository extends JpaRepository<Factory, Long> {

    Optional<Factory> findByFactoryName(String factoryName);
}
