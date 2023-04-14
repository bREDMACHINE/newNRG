package get.a.big.head.newNRG.projectdocumentations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ProjectDocumentationMapper {

    public static ProjectDocumentationDto toProjectDto(String projectName, String projectCode, List<Long> equipment, Long fileId) {
        return ProjectDocumentationDto.builder()
                .nameProjectDocumentation(projectName)
                .codeProjectDocumentation(projectCode)
                .equipment(equipment)
                .fileId(fileId)
                .build();
    }

    public static ProjectDocumentationDto toProjectDto(Object object) {
        return new Gson().fromJson(object.toString(), ProjectDocumentationDto.class);
    }

    public static List<ProjectDocumentationDto> toProjectDtos(Object object) {
        return new Gson().fromJson(object.toString(), new TypeToken<List<ProjectDocumentationDto>>(){}.getType());
    }
}
