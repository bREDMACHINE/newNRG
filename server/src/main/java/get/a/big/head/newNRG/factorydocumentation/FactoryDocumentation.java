package get.a.big.head.newNRG.factorydocumentation;

import get.a.big.head.newNRG.files.DataFile;
import get.a.big.head.newNRG.types.Type;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "documents", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FactoryDocumentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;
    @Column(name = "document_name")
    private String nameFactoryDocumentation;
    @Column(name = "document_code")
    private String codeFactoryDocumentation;
    @ManyToOne
    @JoinColumn(name = "file_id")
    private DataFile file;
    @ManyToMany(mappedBy = "factoryDocuments")
    @ToString.Exclude
    private List<Type> types;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FactoryDocumentation that = (FactoryDocumentation) o;
        return documentId != null && Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
