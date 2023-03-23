package get.a.big.head.newNRG.specifications;

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
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;

    @Override
    public SpecificationDto addSpecification(SpecificationDto specificationDto) {
        if (specificationRepository.findBySpecificationName(specificationDto.getSpecificationName()).isEmpty()) {
            return SpecificationMapper.toSpecificationDto(specificationRepository.save(
                    SpecificationMapper.toSpecification(specificationDto)
            ));
        }
        throw new BadRequestException("Указанная характеристика уже используется");
    }

    @Override
    public void deleteSpecification(Long id) {
        Specification specification = specificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный specificationId не существует"));
        specificationRepository.delete(specification);
    }

    @Override
    public SpecificationDto getSpecification(Long id) {
        return SpecificationMapper.toSpecificationDto(specificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный specificationId не существует")));
    }

    @Override
    public List<SpecificationDto> findAllSpecifications() {
        return specificationRepository.findAll().stream()
                .map(SpecificationMapper::toSpecificationDto)
                .collect(Collectors.toList());
    }
}
