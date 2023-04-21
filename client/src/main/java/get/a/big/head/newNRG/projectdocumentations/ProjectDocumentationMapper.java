package get.a.big.head.newNRG.projectdocumentations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ProjectDocumentationMapper {

    public static ProjectDocumentationDto toProjectDto(String projectName, String projectCode, List<Long> equipment, Long fileId) {
        ProjectDocumentationDto project = new ProjectDocumentationDto();
        project.setNameProjectDocumentation(projectName);
        project.setCodeProjectDocumentation(projectCode);
        project.setEquipment(equipment);
        project.setFileId(fileId);
        return project;
    }

    public static ProjectDocumentationDto toProjectDto(Object object) {
        return new Gson().fromJson(object.toString(), ProjectDocumentationDto.class);
    }

    public static List<ProjectDocumentationDto> toProjectDtos(Object object) {
        return new Gson().fromJson(object.toString(), new TypeToken<List<ProjectDocumentationDto>>(){}.getType());
    }
}
