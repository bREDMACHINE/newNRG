package get.a.big.head.newNRG.types;

import get.a.big.head.newNRG.factories.Factory;
import get.a.big.head.newNRG.factorydocumentation.FactoryDocumentation;
import get.a.big.head.newNRG.spares.Spare;
import get.a.big.head.newNRG.specificationvalue.SpecificationValue;

import java.util.List;
import java.util.stream.Collectors;

public class TypeMapper {

    public static Type toType(TypeShortDto typeShortDto, Factory factory, List<SpecificationValue> specificationValues) {
        Type type = new Type();
        type.setTypeName(typeShortDto.getTypeName());
        type.setFactory(factory);
        type.setSpecificationValues(specificationValues);
        return type;
    }

    public static TypeShortDto toTypeShortDto(Type type) {
        return TypeShortDto.builder()
                .typeId(type.getTypeId())
                .typeName(type.getTypeName())
                .factoryId(type.getFactory().getFactoryId())
                .specificationValues(type.getSpecificationValues().stream()
                        .map(SpecificationValue::getSpecificationValueId)
                        .collect(Collectors.toList()))
                .build();
    }

    public static TypeDto toTypeDto(Type type) {
        return TypeDto.builder()
                .typeId(type.getTypeId())
                .typeName(type.getTypeName())
                .factory(new TypeDto.FactoryDtoForTypeDto(type.getFactory().getFactoryId(), type.getFactory().getFactoryName()))
                .specificationValues(type.getSpecificationValues().stream().map(SpecificationValue::getSpecificationValueId).collect(Collectors.toList()))
                .factoryDocuments(type.getFactoryDocuments().stream().map(FactoryDocumentation::getDocumentId).collect(Collectors.toList()))
                .spares(type.getSpares().stream().map(Spare::getSpareId).collect(Collectors.toList()))
                .build();
    }
}
