package get.newNRG.files;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataFileRepository extends JpaRepository<DataFile, Long> {

    Optional<DataFile> findByName(String name);
}
