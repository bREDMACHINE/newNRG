package get.a.big.head.newNRG.specificationvalue;

import get.a.big.head.newNRG.specifications.Specification;
import get.a.big.head.newNRG.types.Type;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "specificationValues", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SpecificationValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specification_value_id")
    private Long specificationValueId;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "specification_id")
    private Specification specification;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="type_id")
    private Type type;
    @Column(name = "specification_value")
    private Long specificationValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SpecificationValue that = (SpecificationValue) o;
        return specificationValueId != null && Objects.equals(specificationValueId, that.specificationValueId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
