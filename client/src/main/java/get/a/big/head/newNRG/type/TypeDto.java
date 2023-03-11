package get.a.big.head.newNRG.type;

import get.a.big.head.newNRG.factorydocumentations.FactoryDocumentation;
import get.a.big.head.newNRG.spares.Spare;
import get.a.big.head.newNRG.specification.Specification;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TypeDto {
    private Long typeId;
    private String typeName;
    private FactoryDtoForTypeDto factory;
    private List<Long> specifications;
    private List<Long> factoryDocuments;
    private List<Long> spares;

    @Builder
    @Getter
    public static class FactoryDtoForTypeDto {
        Long factoryId;
        String factoryName;
    }
}
