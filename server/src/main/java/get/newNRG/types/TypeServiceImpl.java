package get.newNRG.types;

import get.newNRG.exception.BadRequestException;
import get.newNRG.exception.NotFoundException;
import get.newNRG.factories.Factory;
import get.newNRG.factories.FactoryRepository;
import get.newNRG.factorydocumentation.FactoryDocumentation;
import get.newNRG.factorydocumentation.FactoryDocumentationRepository;
import get.newNRG.spares.Spare;
import get.newNRG.spares.SpareRepository;
import get.newNRG.specificationvalue.SpecificationValue;
import get.newNRG.specificationvalue.SpecificationValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final FactoryRepository factoryRepository;
    private final SpecificationValueRepository specificationValueRepository;
    private final SpareRepository spareRepository;
    private final FactoryDocumentationRepository factoryDocumentationRepository;

    @Override
    public TypeShortDto addType(TypeShortDto typeShortDto) {
        if (typeRepository.findByTypeName(typeShortDto.getTypeName()).isEmpty()) {
            Factory factory = factoryRepository.findById(typeShortDto.getFactoryId())
                    .orElseThrow(() -> new NotFoundException("Указанный factoryId не существует"));
            List<SpecificationValue> specificationValues = specificationValueRepository.findAllById(typeShortDto.getSpecificationValues());
            return TypeMapper.toTypeShortDto(typeRepository.save(
                    TypeMapper.toType(typeShortDto, factory, specificationValues)
            ));
        }
        throw  new BadRequestException("Указанный тип уже используется");
    }

    @Override
    public TypeDto updateType(TypeDto typeDto) {
        Type type = typeRepository.findById(typeDto.getTypeId())
                .orElseThrow(() -> new NotFoundException("Указанный typeId не существует"));
        if (typeDto.getSpares() != null) {
            List<Spare> spares = spareRepository.findAllById(typeDto.getSpares());
            type.setSpares(spares);
        }
        if (typeDto.getFactoryDocuments() != null) {
            List<FactoryDocumentation> factoryDocuments = factoryDocumentationRepository.findAllById(typeDto.getFactoryDocuments());
            type.setFactoryDocuments(factoryDocuments);
        }
        return TypeMapper.toTypeDto(typeRepository.save(type));
    }

    @Override
    public void deleteType(Long id) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный typeId не существует"));
        typeRepository.delete(type);
    }

    @Override
    public TypeDto getType(Long id) {
        return TypeMapper.toTypeDto(typeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный typeId не существует")));
    }

    @Override
    public List<TypeShortDto> findAllTypes() {
        return typeRepository.findAll().stream().map(TypeMapper::toTypeShortDto).collect(Collectors.toList());
    }
}
