package get.a.big.head.newNRG.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddTypeFrameController {

    private final TypeClient typeClient;
    private AddTypeFrame addTypeFrame;

    public void initAddTypeFrameController() {

    }
}
