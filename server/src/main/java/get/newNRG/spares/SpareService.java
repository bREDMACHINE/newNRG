package get.newNRG.spares;

import java.util.List;

public interface SpareService {

    SpareDto addSpare(SpareDto spareDto);
    void deleteSpare(Long id);
    SpareDto getSpare(Long id);
    List<SpareDto> findAllSpares(Long typeId, int from, int size);
}
