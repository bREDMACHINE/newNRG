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
    public DataFileDto addFile(@RequestBody DataFileDto dataFile) {
        log.info("Получен Post запрос к эндпоинту /user/file/ dataFile={}", dataFile);
        DataFileDto dataFileDto = dataFileService.addFile(dataFile);
        log.info("Результат запроса {}", dataFileDto);
        return dataFileDto;
    }

    @GetMapping("/user/file/{id}")
    public DataFileDto getFile(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/file/{}", id);
        DataFileDto dataFileDto = dataFileService.getFile(id);
        log.info("Результат запроса {}", dataFileDto);
        return dataFileDto;
    }
}
