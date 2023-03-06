package get.a.big.head.newNRG.spares;

import get.a.big.head.newNRG.types.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "spares", schema = "public")
@Data
@AllArgsConstructor
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
    @ManyToMany(mappedBy = "types")
    private List<Type> types;
}
