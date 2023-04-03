package get.a.big.head.newNRG.files;

import org.springframework.web.multipart.MultipartFile;

public interface DataFileService {

    MultipartFile getFile(Long fileId);
}
