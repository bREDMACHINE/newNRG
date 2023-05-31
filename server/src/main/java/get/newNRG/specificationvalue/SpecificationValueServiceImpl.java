package get.newNRG.specificationvalue;

import get.newNRG.exception.NotFoundException;
import get.newNRG.specifications.Specification;
import get.newNRG.specifications.SpecificationRepository;
import get.newNRG.types.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//        Type type = typeRepository.findById(specificationValueDto.getTypeId())
//                .orElseThrow(() -> new NotFoundException("Указанный typeId не существует"));
        return SpecificationValueMapper.toSpecificationValueDto(specificationValueRepository.save(
                SpecificationValueMapper.toSpecificationValue(specificationValueDto, specification)
        ));
    }

    @Override
    public ResponseEntity<?> deleteSpecificationValue(Long id) {
        SpecificationValue specificationValue = specificationValueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный specificationValueId не существует"));
        String name = specificationValue.getSpecificationValue().toString();
        specificationValueRepository.delete(specificationValue);
        Map<String, String> response = new HashMap<>();
        response.put("name", name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public SpecificationValueDto getSpecificationValue(Long id) {
        return SpecificationValueMapper.toSpecificationValueDto(specificationValueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный specificationValueId не существует")));
    }

    @Override
    public List<SpecificationValueDto> findAllSpecificationValues(Long typeId) {
        return specificationValueRepository.findByTypeTypeId(typeId).stream()
                .map(SpecificationValueMapper::toSpecificationValueDto)
                .collect(Collectors.toList());
    }
}
