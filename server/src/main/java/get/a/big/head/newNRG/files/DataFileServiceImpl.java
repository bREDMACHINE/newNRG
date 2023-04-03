package get.a.big.head.newNRG.files;

import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class DataFileServiceImpl implements DataFileService {

    private final DataFileRepository dataFileRepository;

    @Override
    public MultipartFile getFile(Long fileId) {
        return new DataFileToMultipart(dataFileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException("Указанный fileId не существует")));
    }
}
