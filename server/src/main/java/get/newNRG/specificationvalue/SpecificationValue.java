package get.newNRG.specificationvalue;

import get.newNRG.specifications.Specification;
import get.newNRG.types.Type;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "values", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SpecificationValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "value_id")
    private Long specificationValueId;
    @ManyToOne
    @JoinColumn(name = "specification_id")
    private Specification specification;
    @ManyToOne
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
