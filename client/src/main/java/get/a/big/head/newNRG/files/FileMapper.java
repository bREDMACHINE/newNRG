package get.a.big.head.newNRG.files;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMapper {

    public static DataFile toDataFile(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), DataFile.class);
    }

    public static DataFile toDataFile(File file) {
        String contentType;
        byte[] bytes;
        try {
            contentType = Files.probeContentType(file.toPath());
            bytes = Files.readAllBytes(Path.of(file.getAbsolutePath()));
        } catch (IOException e) {
            throw new RuntimeException("Файл не загружен", e);
        }

        return DataFile.builder()
                .name(file.getName())
                .contentType(contentType)
                .isEmpty(file.length() == 0)
                .size(file.length())
                .content(bytes)
                .build();

    }
}
