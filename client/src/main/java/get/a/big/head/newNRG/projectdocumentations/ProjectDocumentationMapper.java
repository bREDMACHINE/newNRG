package get.a.big.head.newNRG.projectdocumentations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import get.a.big.head.newNRG.specification.SpecificationDto;

import java.util.List;

public class ProjectDocumentationMapper {

    public static ProjectDocumentationDto toProjectDto(String projectName, String projectCode, String projectFile) {
        return ProjectDocumentationDto.builder()
                .nameProjectDocumentation(projectName)
                .codeProjectDocumentation(projectCode)
                .projectDocumentation(projectFile)
                .build();
    }

    public static ProjectDocumentationDto toProjectDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), ProjectDocumentationDto.class);
    }

    public static List<ProjectDocumentationDto> toProjectDtos(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<ProjectDocumentationDto>>(){}.getType());
    }
}
