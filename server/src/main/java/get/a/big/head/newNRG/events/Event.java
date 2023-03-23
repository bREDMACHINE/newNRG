package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.equipment.Equipment;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "events", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_event")
    private LocalDateTime createEvent;
    @Column(name = "name_event")
    private String nameEvent;
    @Column(name = "description_event")
    private String descriptionEvent;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="equipment_id")
    private Equipment equipment;
    @Column(name = "file")
    private String documentEvent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return eventId != null && Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
