package get.a.big.head.newNRG.types;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TypeDto {

    private Long typeId;
    private String typeName;
    private FactoryDtoForTypeDto factory;
    private List<Long> specificationValues;
    private List<Long> factoryDocuments;
    private List<Long> spares;

    @Builder
    @Getter
    public static class FactoryDtoForTypeDto {
        Long factoryId;
        String factoryName;
    }
}
