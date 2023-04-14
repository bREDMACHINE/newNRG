package get.a.big.head.newNRG.factorydocumentations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class FactoryDocumentationMapper {

    public static FactoryDocumentationDto toDocumentDto(String documentName, String documentCode, List<Long> types, Long fileId) {
        return FactoryDocumentationDto.builder()
                .nameFactoryDocumentation(documentName)
                .codeFactoryDocumentation(documentCode)
                .types(types)
                .fileId(fileId)
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
