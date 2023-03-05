package get.a.big.head.newNRG.equipment;

import get.a.big.head.newNRG.events.Event;
import get.a.big.head.newNRG.projectdocumentations.ProjectDocumentation;
import get.a.big.head.newNRG.type.Type;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class Equipment {

    private Long id;
    private String operationalName;
    private String installationYear;
    private Type type;
    private List<ProjectDocumentation> projectDocuments = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
}
