package get.a.big.head.newNRG.type;

import get.a.big.head.newNRG.factorydocumentations.FactoryDocumentation;
import get.a.big.head.newNRG.spares.Spare;
import get.a.big.head.newNRG.specification.Specification;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
public class Type {

    private String typeName;
    private String factoryName;
    private Set<Specification> specifications = new HashSet<>();
    private Set<FactoryDocumentation> factoryDocumentations = new HashSet<>();
    private HashMap<Spare, Long> spares = new HashMap<>();
}
