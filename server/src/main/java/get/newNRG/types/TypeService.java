package get.newNRG.types;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TypeService {

    TypeShortDto addType(TypeShortDto typeShortDto);
    TypeDto updateType(TypeDto typeDto);
    ResponseEntity<?> deleteType(Long id);
    TypeDto getType(Long id);
    List<TypeShortDto> findAllTypes();
}
