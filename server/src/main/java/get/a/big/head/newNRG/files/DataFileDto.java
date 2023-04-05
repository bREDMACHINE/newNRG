package get.a.big.head.newNRG.files;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class DataFileDto {

    private Long fileId;
    private String name;
    private String contentType;
    private Boolean isEmpty;
    private Long size;
    private byte[] content;
}
