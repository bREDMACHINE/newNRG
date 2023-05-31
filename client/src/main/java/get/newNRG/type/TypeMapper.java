package get.newNRG.type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class TypeMapper {

    public static List<TypeShortDto> toTypeShortDtos(Object object) {
        return new Gson().fromJson(object.toString(), new TypeToken<List<TypeShortDto>>(){}.getType());
    }

    public static TypeDto toTypeDto(Object object) {
        return new Gson().fromJson(object.toString(), TypeDto.class);
    }

    public static TypeShortDto toTypeShortDto(String typeName, Long factoryId, List<Long> specificationsValues) {
        return TypeShortDto.builder()
                .typeName(typeName)
                .factoryId(factoryId)
                .specifications(specificationsValues)
                .build();
    }

    public static TypeShortDto toTypeShortDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), TypeShortDto.class);
    }
}
