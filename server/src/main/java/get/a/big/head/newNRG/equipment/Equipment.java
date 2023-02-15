package get.a.big.head.newNRG.equipment;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long id;
    @Column(name = "operational_name")
    private String operationalName;
    @Column(name = "rated_current")
    private String ratedCurrent;
    @Column(name = "rated_voltage")
    private String ratedVoltage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Equipment equipment = (Equipment) o;
        return id != null && Objects.equals(id, equipment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
