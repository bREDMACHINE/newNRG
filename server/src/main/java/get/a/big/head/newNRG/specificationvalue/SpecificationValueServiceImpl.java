package get.a.big.head.newNRG.specificationvalue;

import get.a.big.head.newNRG.exception.NotFoundException;
import get.a.big.head.newNRG.specifications.Specification;
import get.a.big.head.newNRG.specifications.SpecificationRepository;
import get.a.big.head.newNRG.types.Type;
import get.a.big.head.newNRG.types.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class SpecificationValueServiceImpl implements SpecificationValueService {

    private final SpecificationValueRepository specificationValueRepository;
    private final SpecificationRepository specificationRepository;
    private final TypeRepository typeRepository;

    @Override
    public SpecificationValueDto addSpecificationValue(SpecificationValueDto specificationValueDto) {
        Specification specification = specificationRepository.findById(
                specificationValueDto.getSpecification().getSpecificationId())
                .orElseThrow(() -> new NotFoundException("Указанный specificationId не существует"));
        Type type = typeRepository.findById(specificationValueDto.getTypeId())
                .orElseThrow(() -> new NotFoundException("Указанный typeId не существует"));
        return SpecificationValueMapper.toSpecificationValueDto(specificationValueRepository.save(
                SpecificationValueMapper.toSpecificationValue(specificationValueDto, specification, type)
        ));
    }

    @Override
    public void deleteSpecificationValue(Long id) {
        SpecificationValue specificationValue = specificationValueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный specificationValueId не существует"));
        specificationValueRepository.delete(specificationValue);
    }

    @Override
    public SpecificationValueDto getSpecificationValue(Long id) {
        return SpecificationValueMapper.toSpecificationValueDto(specificationValueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный specificationValueId не существует")));
    }

    @Override
    public List<SpecificationValueDto> findAllSpecificationValues(Long id, int from, int size) {
        return specificationValueRepository.findByTypeTypeId(id).stream()
                .map(SpecificationValueMapper::toSpecificationValueDto)
                .collect(Collectors.toList());
    }
}
