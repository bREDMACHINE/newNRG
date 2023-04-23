package get.newNRG.types;

import get.newNRG.factories.Factory;
import get.newNRG.factorydocumentation.FactoryDocumentation;
import get.newNRG.spares.Spare;
import get.newNRG.specificationvalue.SpecificationValue;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @OneToMany (mappedBy="type")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<SpecificationValue> specificationValues;
    @ManyToMany
    @JoinTable(
            name = "types_documents",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    @ToString.Exclude
    private List<FactoryDocumentation> factoryDocuments;
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
