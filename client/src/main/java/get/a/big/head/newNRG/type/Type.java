package get.a.big.head.newNRG.type;

import get.a.big.head.newNRG.factorydocumentations.FactoryDocumentation;
import get.a.big.head.newNRG.spares.Spare;
import get.a.big.head.newNRG.specification.Specification;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Type {
    private Long typeId;
    private String typeName;
    private String factoryName;
    private List<Specification> specifications;
    private List<FactoryDocumentation> factoryDocumentations;
    private List<Spare> spares;
}
