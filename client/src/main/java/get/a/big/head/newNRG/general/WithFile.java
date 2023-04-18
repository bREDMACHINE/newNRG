package get.a.big.head.newNRG.general;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public abstract class WithFile {

    private Long id;
    private Long fileId;
}
