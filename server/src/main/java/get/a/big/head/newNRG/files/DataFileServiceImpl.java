package get.a.big.head.newNRG.files;

import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class DataFileServiceImpl implements DataFileService {

    private final DataFileRepository dataFileRepository;

    @Override
    public DataFileFullDto getFile(Long fileId) {
        return DataFileMapper.toDataFileFullDto(dataFileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException("Указанный fileId не существует")));
    }

    @Override
    public DataFileDto addFile(DataFileDto dataFile) {
        return DataFileMapper.toDataFileDto(dataFileRepository.save(DataFileMapper.toDataFile(dataFile)));
    }
}
