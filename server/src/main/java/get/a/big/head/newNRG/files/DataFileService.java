package get.a.big.head.newNRG.files;

public interface DataFileService {

    DataFileDto getFile(Long fileId);
    DataFileDto addFile(DataFileDto dataFile);
}
