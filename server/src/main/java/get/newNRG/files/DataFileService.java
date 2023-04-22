package get.newNRG.files;

public interface DataFileService {

    DataFileFullDto getFile(Long fileId);
    DataFileDto addFile(DataFileDto dataFile);
}
