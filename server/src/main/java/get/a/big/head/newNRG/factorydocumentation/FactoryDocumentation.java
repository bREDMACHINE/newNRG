package get.a.big.head.newNRG.factorydocumentation;

import get.a.big.head.newNRG.types.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "documents", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactoryDocumentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long projectId;
    @Column(name = "document_name")
    private String nameFactoryDocumentation;
    @Column(name = "document_code")
    private String codeFactoryDocumentation;
    @Column(name = "file")
    private String factoryDocumentation;
    @ManyToMany(mappedBy = "types")
    private List<Type> equipment;
}
