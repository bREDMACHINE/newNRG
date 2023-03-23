package get.a.big.head.newNRG.equipment;

import get.a.big.head.newNRG.events.Event;
import get.a.big.head.newNRG.projectdocumentation.ProjectDocumentation;
import get.a.big.head.newNRG.types.Type;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "equipment", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "operational_name")
    private String operationalName;
    @Column(name = "installation_year")
    private Short installationYear;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToMany
    @JoinTable(
            name = "equipment_projects",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @ToString.Exclude
    private List<ProjectDocumentation> projectDocuments;
    @OneToMany (mappedBy="equipment", fetch=FetchType.EAGER)
    private List<Event> events;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Equipment equipment = (Equipment) o;
        return equipmentId != null && Objects.equals(equipmentId, equipment.equipmentId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
