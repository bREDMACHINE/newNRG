package get.newNRG.factories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class FactoryMapper {

    public static FactoryDto toFactoryDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), FactoryDto.class);
    }

    public static List<FactoryDto> toFactoryDtos(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<FactoryDto>>(){}.getType());
    }
}
