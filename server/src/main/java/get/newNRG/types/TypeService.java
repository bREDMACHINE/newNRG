package get.newNRG.types;

import java.util.List;

public interface TypeService {

    TypeShortDto addType(TypeShortDto typeShortDto);
    TypeDto updateType(TypeDto typeDto);
    void deleteType(Long id);
    TypeDto getType(Long id);
    List<TypeShortDto> findAllTypes();
}
