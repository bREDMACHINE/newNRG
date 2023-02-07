package get.a.big.head.newNRG.exception;

import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorMapper {

    public static Error toError(Object object) {
        Map<String, String> map = (LinkedHashMap<String, String>) object;
        Error error = new Error();
        error.setMessage(map.get("Error"));
        error.setStatus(map.get("Status"));
        return error;
    }
}
