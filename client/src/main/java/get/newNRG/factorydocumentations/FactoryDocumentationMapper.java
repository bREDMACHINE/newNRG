package get.newNRG.factorydocumentations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class FactoryDocumentationMapper {

    public static FactoryDocumentationDto toDocumentDto(String documentName, String documentCode, List<Long> types, Long fileId) {
        FactoryDocumentationDto document = new FactoryDocumentationDto();
        document.setNameFactoryDocumentation(documentName);
        document.setCodeFactoryDocumentation(documentCode);
        document.setTypes(types);
        document.setFileId(fileId);
        return document;
    }

    public static FactoryDocumentationDto toDocumentDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), FactoryDocumentationDto.class);
    }

    public static List<FactoryDocumentationDto> toDocumentDtos(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<FactoryDocumentationDto>>(){}.getType());
    }
}
