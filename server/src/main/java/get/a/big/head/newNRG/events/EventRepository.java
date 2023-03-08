package get.a.big.head.newNRG.events;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByEquipmentEquipmentId(Long id);
}
