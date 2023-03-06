package get.a.big.head.newNRG.equipment;

import get.a.big.head.newNRG.projectdocumentation.ProjectDocumentation;
import get.a.big.head.newNRG.types.Type;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "equipment", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "operational_name")
    private String operationalName;
    @Column(name = "installation_year")
    private LocalDate installationYear;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToMany
    @JoinTable(
            name = "equipment_projects",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<ProjectDocumentation> projectDocuments;
}
