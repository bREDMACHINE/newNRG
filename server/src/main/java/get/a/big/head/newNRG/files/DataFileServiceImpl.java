package get.a.big.head.newNRG.files;

import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        Optional<DataFile> fileOptional = dataFileRepository.findByName(dataFile.getName());
        DataFile newFile = DataFileMapper.toDataFile(dataFile);
        if (fileOptional.isPresent()) {
            if (fileOptional.get().equals(newFile)) {
                return DataFileMapper.toDataFileDto(fileOptional.get());
            }
        }
        return DataFileMapper.toDataFileDto(dataFileRepository.save(newFile));
    }
}
