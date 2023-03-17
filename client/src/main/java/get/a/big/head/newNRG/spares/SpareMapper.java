package get.a.big.head.newNRG.spares;

import com.google.gson.Gson;
import get.a.big.head.newNRG.events.EventDto;

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
}
