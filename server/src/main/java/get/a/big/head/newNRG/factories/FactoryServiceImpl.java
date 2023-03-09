package get.a.big.head.newNRG.factories;

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
public class FactoryServiceImpl implements FactoryService {

    private final FactoryRepository factoryRepository;

    @Override
    public FactoryDto addFactory(FactoryDto factoryDto) {
        if (factoryRepository.findByFactoryName(factoryDto.getFactoryName()).isEmpty()) {
            return FactoryMapper.toFactoryDto(factoryRepository.save(
                    FactoryMapper.toFactory(factoryDto)
            ));
        }
        throw new BadRequestException("Указанный завод уже используется");
    }

    @Override
    public void deleteFactory(Long id) {
        Factory factory = factoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный factoryId не существует"));
        factoryRepository.delete(factory);
    }

    @Override
    public FactoryDto getFactory(Long id) {
        return FactoryMapper.toFactoryDto(factoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный factoryId не существует")));
    }

    @Override
    public List<FactoryDto> findAllFactories() {
        return factoryRepository.findAll().stream().map(FactoryMapper::toFactoryDto).collect(Collectors.toList());
    }
}
