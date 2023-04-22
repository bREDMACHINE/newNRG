package get.newNRG.spares;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SpareMapper {

    public static SpareDto toSpareDto(String spareName, String spareDescription, String spareCode) {
        return SpareDto.builder()
                .spareName(spareName)
                .spareDescription(spareDescription)
                .spareCode(spareCode)
                .build();
    }

    public static SpareDto toSpareDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), SpareDto.class);
    }

    public static List<SpareDto> toSpareDtos(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<SpareDto>>(){}.getType());
    }
}
