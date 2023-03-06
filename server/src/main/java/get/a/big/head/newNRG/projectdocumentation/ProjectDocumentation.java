package get.a.big.head.newNRG.projectdocumentation;

import get.a.big.head.newNRG.equipment.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDocumentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "project_name")
    private String nameProjectDocumentation;
    @Column(name = "project_code")
    private String codeProjectDocumentation;
    @Column(name = "file")
    private String projectDocumentation;
    @ManyToMany(mappedBy = "equipment")
    private List<Equipment> equipment;
}
