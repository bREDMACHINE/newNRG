package get.newNRG.events;

import get.newNRG.equipment.Equipment;
import get.newNRG.files.DataFile;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
    @Column(name = "date_event")
    private String dateEvent;
    @Column(name = "name_event")
    private String nameEvent;
    @Column(name = "description_event")
    private String descriptionEvent;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="equipment_id")
    private Equipment equipment;
    @ManyToOne
    @JoinColumn(name = "file_id")
    private DataFile documentEvent;

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
