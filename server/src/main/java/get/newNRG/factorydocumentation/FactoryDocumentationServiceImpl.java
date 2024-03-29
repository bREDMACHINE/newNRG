package get.newNRG.factorydocumentation;

import get.newNRG.exception.BadRequestException;
import get.newNRG.exception.NotFoundException;
import get.newNRG.files.DataFile;
import get.newNRG.files.DataFileRepository;
import get.newNRG.types.Type;
import get.newNRG.types.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class FactoryDocumentationServiceImpl implements FactoryDocumentationService {

    private final FactoryDocumentationRepository factoryDocumentationRepository;
    private final TypeRepository typeRepository;
    private final DataFileRepository dataFileRepository;

    @Override
    public FactoryDocumentationDto addDocument(FactoryDocumentationDto factoryDocumentationDto) {
        List<Type> types = typeRepository.findAllById(factoryDocumentationDto.getTypes());
        DataFile dataFile = dataFileRepository.findById(factoryDocumentationDto.getFileId())
                .orElseThrow(() -> new NotFoundException("Указанный fileId не существует"));
        if (factoryDocumentationRepository.findByNameFactoryDocumentation(
                factoryDocumentationDto.getNameFactoryDocumentation()
        ).isEmpty()) {
            return FactoryDocumentationMapper.toFactoryDocumentationDto(factoryDocumentationRepository.save(
                    FactoryDocumentationMapper.toFactoryDocumentation(factoryDocumentationDto, types, dataFile)
            ));
        }
        throw  new BadRequestException("Указанный документ уже используется");
    }

    @Override
    public void deleteDocument(Long id) {
        FactoryDocumentation factoryDocumentation = factoryDocumentationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный documentId не существует"));
        factoryDocumentationRepository.delete(factoryDocumentation);
    }

    @Override
    public FactoryDocumentationDto getDocument(Long id) {
        return FactoryDocumentationMapper.toFactoryDocumentationDto(factoryDocumentationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный documentId не существует")));
    }

    @Override
    public List<FactoryDocumentationDto> findAllDocuments(Long typeId, int from, int size) {
        return factoryDocumentationRepository.findByTypesTypeId(typeId, PageRequest.of(from / size, size)).stream()
                .map(FactoryDocumentationMapper::toFactoryDocumentationDto)
                .collect(Collectors.toList());
    }
}
