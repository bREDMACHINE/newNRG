package get.newNRG.files;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DataFileFullDto {

    private Long fileId;
    private String name;
    private String contentType;
    private Boolean isEmpty;
    private Long size;
    private String content;
}
