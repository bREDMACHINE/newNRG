package get.newNRG.spares;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SpareMapper {

    public static SpareDto toSpareDto(String spareName, String spareDescription, String spareCode) {
        SpareDto spareDto = new SpareDto();
        spareDto.setSpareName(spareName);
        spareDto.setSpareDescription(spareDescription);
        spareDto.setSpareCode(spareCode);
        return spareDto;
    }

    public static SpareDto toSpareDto(Object object) {
        return new Gson().fromJson(object.toString(), SpareDto.class);
    }

    public static List<SpareDto> toSpareDtos(Object object) {
        return new Gson().fromJson(object.toString(), new TypeToken<List<SpareDto>>(){}.getType());
    }
}
