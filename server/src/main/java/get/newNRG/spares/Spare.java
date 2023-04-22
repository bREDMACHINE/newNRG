package get.newNRG.spares;

import get.newNRG.types.Type;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "spares", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Spare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spare_id")
    private Long spareId;
    @Column(name = "spare_name")
    private String spareName;
    @Column(name = "spare_description")
    private String spareDescription;
    @Column(name = "spare_code")
    private String spareCode;
    @ManyToMany(mappedBy = "spares")
    @ToString.Exclude
    private List<Type> types;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Spare spare = (Spare) o;
        return spareId != null && Objects.equals(spareId, spare.spareId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
