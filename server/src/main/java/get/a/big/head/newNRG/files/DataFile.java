package get.a.big.head.newNRG.files;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "files", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DataFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;
    @Column(name = "file_name")
    private String name;
    @Column(name = "file_type")
    private String type;
    @Column(name = "file_size")
    private Long size;
    @Column(name = "file_content")
    private byte[] content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DataFile dataFile = (DataFile) o;
        return fileId != null && Objects.equals(fileId, dataFile.fileId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
