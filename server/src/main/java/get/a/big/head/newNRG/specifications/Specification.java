package get.a.big.head.newNRG.specifications;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.types.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specifications", schema = "public")
@Data
@AllArgsConstructor
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
    private List<Type> types;
}
