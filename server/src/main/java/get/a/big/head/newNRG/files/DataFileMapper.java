package get.a.big.head.newNRG.files;

public class DataFileMapper {

    public static DataFile toDataFile(DataFileDto dataFileDto) {
        DataFile dataFile = new DataFile();
        dataFile.setName(dataFileDto.getName());
        dataFile.setType(dataFileDto.getContentType());
        dataFile.setSize(dataFileDto.getSize());
        dataFile.setContent(dataFileDto.getContent());
        return dataFile;
    }

    public static DataFileDto DataFileToDto(DataFile dataFile) {
        return DataFileDto.builder()
                .fileId(dataFile.getFileId())
                .name(dataFile.getName())
                .contentType(dataFile.getType())
                .isEmpty(dataFile.getSize() == 0)
                .size(dataFile.getSize())
                .content(dataFile.getContent())
                .build();
    }

    public static DataFileDto toDataFileDto(DataFile dataFile) {
        return DataFileDto.builder()
                .fileId(dataFile.getFileId())
                .name(dataFile.getName())
                .contentType(dataFile.getType())
                .isEmpty(dataFile.getSize() == 0)
                .size(dataFile.getSize())
                .build();
    }
}
