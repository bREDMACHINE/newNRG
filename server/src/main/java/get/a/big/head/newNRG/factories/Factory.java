package get.a.big.head.newNRG.factories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "factories", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factory_id")
    private Long factoryId;
    @Column(name = "factory_name")
    private String factoryName;
}
