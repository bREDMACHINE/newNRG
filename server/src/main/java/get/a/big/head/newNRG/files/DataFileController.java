package get.a.big.head.newNRG.files;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class DataFileController {

    private final DataFileService dataFileService;

    @GetMapping("/user/file/{id}")
    public MultipartFile getFile(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/file/{}", id);
        return dataFileService.getFile(id);
    }
}
