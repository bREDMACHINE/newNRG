package get.a.big.head.newNRG.files;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DataFile {

    private Long fileId;
    private String name;
    private String contentType;
    private Boolean isEmpty;
    private Long size;
    private byte[] content;
}
