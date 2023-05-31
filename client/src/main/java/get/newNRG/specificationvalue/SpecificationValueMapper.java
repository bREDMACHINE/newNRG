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

    public static SpecificationValueDto toSpecificationValueDto(Object object) {
        return new Gson().fromJson(object.toString(), SpecificationValueDto.class);
    }

    public static List<SpecificationValueDto> toSpecificationValueDtos(Object object) {
        return new Gson().fromJson(object.toString(), new TypeToken<List<SpecificationValueDto>>(){}.getType());
    }
}
