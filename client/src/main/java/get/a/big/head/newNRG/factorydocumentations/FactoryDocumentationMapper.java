package get.a.big.head.newNRG.factorydocumentations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class FactoryDocumentationMapper {

    public static FactoryDocumentationDto toDocumentDto(String documentName, String documentCode, String documentFile) {
        return FactoryDocumentationDto.builder()
                .nameFactoryDocumentation(documentName)
                .codeFactoryDocumentation(documentCode)
                .factoryDocumentation(documentFile)
                .build();
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
