package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.equipment.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "create_event")
    private LocalDate createEvent;
    @Column(name = "name_event")
    private String nameEvent;
    @Column(name = "decsription_event")
    private String descriptionEvent;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    @Column(name = "file")
    private String documentEvent;
}
