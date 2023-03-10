package get.a.big.head.newNRG.factorydocumentation;

import get.a.big.head.newNRG.exception.BadRequestException;
import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class FactoryDocumentationServiceImpl implements FactoryDocumentationService {

    private final FactoryDocumentationRepository factoryDocumentationRepository;

    @Override
    public FactoryDocumentationDto addDocument(FactoryDocumentationDto factoryDocumentationDto) {
        if (factoryDocumentationRepository.findByNameFactoryDocumentation(
                factoryDocumentationDto.getNameFactoryDocumentation()
        ).isEmpty()) {
            return FactoryDocumentationMapper.toFactoryDocumentationDto(factoryDocumentationRepository.save(
                    FactoryDocumentationMapper.toFactoryDocumentation(factoryDocumentationDto)
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
    public List<FactoryDocumentationDto> findAllDocuments(Long id) {
        return factoryDocumentationRepository.findAllBy(id).stream()
                .map(FactoryDocumentationMapper::toFactoryDocumentationDto)
                .collect(Collectors.toList());
    }
}
