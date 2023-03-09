package get.a.big.head.newNRG.factories;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "factories", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factory_id")
    private Long factoryId;
    @Column(name = "factory_name")
    private String factoryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Factory factory = (Factory) o;
        return factoryId != null && Objects.equals(factoryId, factory.factoryId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
