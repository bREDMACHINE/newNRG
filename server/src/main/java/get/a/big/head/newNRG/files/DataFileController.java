package get.a.big.head.newNRG.files;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class DataFileController {

    private final DataFileService dataFileService;

    @PostMapping("/user/file")
    public DataFileDto addFile(@RequestBody DataFileDto dataFileDto) {
        log.info("Получен Post запрос к эндпоинту /user/file/ dataFile={}", dataFileDto);
        DataFileDto dataFile = dataFileService.addFile(dataFileDto);
        log.info("Результат запроса {}", dataFile);
        return dataFile;
    }

    @GetMapping("/user/file/{id}")
    public DataFileFullDto getFile(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/file/{}", id);
        DataFileFullDto dataFileFullDto = dataFileService.getFile(id);
        log.info("Результат запроса {}", dataFileFullDto);
        return dataFileFullDto;
    }
}
