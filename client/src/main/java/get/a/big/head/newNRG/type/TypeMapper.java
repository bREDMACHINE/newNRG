package get.a.big.head.newNRG.type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class TypeMapper {

    public static List<TypeShortDto> toTypeShortDtos(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<TypeShortDto>>(){}.getType());
    }

    public static TypeDto toTypeDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), TypeDto.class);
    }
}
