package get.a.big.head.newNRG.files;

import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;

public class FileMapper {

    public static MultipartFile toMultipart(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), MultipartFile.class);
    }
}
