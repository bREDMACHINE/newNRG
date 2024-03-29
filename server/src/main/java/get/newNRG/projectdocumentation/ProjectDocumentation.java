package get.newNRG.projectdocumentation;

import get.newNRG.equipment.Equipment;
import get.newNRG.files.DataFile;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projects", schema = "public")
@Getter
@Setter
@ToString
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
    @ManyToOne
    @JoinColumn(name = "file_id")
    private DataFile file;
    @ManyToMany(mappedBy = "projectDocuments")
    @ToString.Exclude
    private List<Equipment> equipment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectDocumentation that = (ProjectDocumentation) o;
        return projectId != null && Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
