package get.a.big.head.newNRG.files;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMapper {

    public static DataFile toDataFile(Object object) {
        System.out.println(object);
        JsonElement jsonElement = JsonParser.parseString(object.toString());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Long fileId = jsonObject.get("fileId").getAsLong();
        String name = jsonObject.get("name").getAsString();
        String contentType = jsonObject.get("contentType").getAsString();
        Boolean isEmpty = jsonObject.get("isEmpty").getAsBoolean();
        Long size = jsonObject.get("size").getAsLong();
        byte[] content = new byte[20971520];
        if (!jsonObject.get("content").isJsonNull()) {
            content = jsonObject.get("content").getAsBigInteger().toByteArray();
        }
        String type = contentType.replace("&", "/");
        return DataFile.builder()
                .fileId(fileId)
                .name(name)
                .contentType(type)
                .isEmpty(isEmpty)
                .size(size)
                .content(content)
                .build();
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
        String type = contentType.replace("/", "&");
        return DataFile.builder()
                .name(file.getName())
                .contentType(type)
                .isEmpty(file.length() == 0)
                .size(file.length())
                .content(bytes)
                .build();

    }
}
