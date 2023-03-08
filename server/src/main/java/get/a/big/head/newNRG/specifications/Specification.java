package get.a.big.head.newNRG.specifications;

import get.a.big.head.newNRG.types.Type;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "specifications", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specification_id")
    private Long specificationId;
    @Column(name = "specification_name")
    private String specificationName;
    @Column(name = "specification_description")
    private String specificationDescription;
    @Column(name = "value")
    private String specificationValue;
    @ManyToMany(mappedBy = "types")
    @ToString.Exclude
    private List<Type> types;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Specification that = (Specification) o;
        return specificationId != null && Objects.equals(specificationId, that.specificationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
