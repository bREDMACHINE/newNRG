package get.newNRG.specificationvalue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SpecificationValueMapper {

    public static SpecificationValueDto toSpecificationValueDto(Long specificationValue, Long specificationId) {
        return SpecificationValueDto.builder()
                .specificationValue(specificationValue)
                .specification(SpecificationValueDto.SpecificationDtoForSpecificationValueDto.builder()
                        .specificationId(specificationId)
                        .build())
                .build();
    }

    public static List<SpecificationValueDto> toSpecificationValueDtos(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<SpecificationValueDto>>(){}.getType());
    }
}
