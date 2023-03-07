package get.a.big.head.newNRG.types;

import get.a.big.head.newNRG.factories.Factory;
import get.a.big.head.newNRG.factorydocumentation.FactoryDocumentation;
import get.a.big.head.newNRG.spares.Spare;
import get.a.big.head.newNRG.specifications.Specification;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "types", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;
    @Column(name = "type_name")
    private String typeName;
    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory factory;
    @ManyToMany
    @JoinTable(
            name = "types_documents",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    @ToString.Exclude
    private List<FactoryDocumentation> factoryDocuments;
    @ManyToMany
    @JoinTable(
            name = "types_specifications",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "specification_id"))
    @ToString.Exclude
    private List<Specification> specifications;
    @ManyToMany
    @JoinTable(
            name = "types_spares",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_id"))
    @ToString.Exclude
    private List<Spare> spares;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Type type = (Type) o;
        return typeId != null && Objects.equals(typeId, type.typeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
