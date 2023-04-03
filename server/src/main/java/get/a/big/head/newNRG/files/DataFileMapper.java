package get.a.big.head.newNRG.files;

import get.a.big.head.newNRG.events.AddEventDto;

public class DataFileMapper {

    public static DataFile toDataFile(AddEventDto addEventDto) {
        DataFile dataFile = new DataFile();
        dataFile.setName(addEventDto.getDocumentEvent().getName());
        dataFile.setType(addEventDto.getDocumentEvent().getType());
        dataFile.setSize(addEventDto.getDocumentEvent().getSize());
        dataFile.setContent(addEventDto.getDocumentEvent().getContent());
        return dataFile;
    }
}
