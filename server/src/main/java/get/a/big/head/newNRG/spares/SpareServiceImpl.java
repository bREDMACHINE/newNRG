package get.a.big.head.newNRG.spares;

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
public class SpareServiceImpl implements SpareService {

    private final SpareRepository spareRepository;

    @Override
    public SpareDto addSpare(SpareDto spareDto) {
        if (spareRepository.findBySpareName(spareDto.getSpareName()).isEmpty()) {
            return SpareMapper.toSpareDto(spareRepository.save(
                    SpareMapper.toSpare(spareDto)
            ));
        }
        throw new BadRequestException("Указанная запчасть уже используется");
    }

    @Override
    public void deleteSpare(Long id) {
        Spare spare = spareRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный spareId не существует"));
        spareRepository.delete(spare);
    }

    @Override
    public SpareDto getSpare(Long id) {
        return SpareMapper.toSpareDto(spareRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный spareId не существует")));
    }

    @Override
    public List<SpareDto> findAllSpares(Long typeId) {
        return spareRepository.findAll().stream().map(SpareMapper::toSpareDto).collect(Collectors.toList());
    }
}
