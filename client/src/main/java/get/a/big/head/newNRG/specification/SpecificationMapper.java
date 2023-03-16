package get.a.big.head.newNRG.specification;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SpecificationMapper {
    public static List<SpecificationDto> toSpecificationDtos(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<SpecificationDto>>(){}.getType());
    }

    public static SpecificationDto toSpecificationDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), SpecificationDto.class);
    }
}
