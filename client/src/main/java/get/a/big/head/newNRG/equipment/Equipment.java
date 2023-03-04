package get.a.big.head.newNRG.equipment;

import get.a.big.head.newNRG.events.Event;
import get.a.big.head.newNRG.projectdocumentations.ProjectDocumentation;
import get.a.big.head.newNRG.type.Type;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class Equipment {

    private String operationalName;
    private String installationYear;
    private Type type;
    private Set<ProjectDocumentation> projectDocuments = new HashSet<>();
    private List<Event> events = new ArrayList<>();
}
