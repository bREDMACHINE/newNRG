package get.newNRG.files;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;

public class DataFileMapper {

    public static DataFileDto toDataFileDto(Object object) {
        Reader reader = new StringReader(object.toString());
        JsonElement jsonElement = JsonParser.parseReader(reader);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Long fileId = jsonObject.get("fileId").getAsLong();
        String name = jsonObject.get("name").getAsString();
        String contentType = jsonObject.get("contentType").getAsString();
        Boolean isEmpty = jsonObject.get("isEmpty").getAsBoolean();
        Long size = jsonObject.get("size").getAsLong();
        String type = contentType.replace("&", "/");
        if (!jsonObject.get("content").isJsonNull()) {
            JsonArray jsonArray = jsonObject.get("content").getAsJsonArray();
            byte[] content = new byte[jsonArray.size()];

            for (int i = 0; i < jsonArray.size(); i++) {
                content[i] = jsonArray.get(i).getAsByte();
            }

            return DataFileDto.builder()
                    .fileId(fileId)
                    .name(name)
                    .contentType(type)
                    .isEmpty(isEmpty)
                    .size(size)
                    .content(content)
                    .build();
        }
        return DataFileDto.builder()
                .fileId(fileId)
                .name(name)
                .contentType(type)
                .isEmpty(isEmpty)
                .size(size)
                .build();
    }

    public static DataFileDto toDataFileDto(File file) {
        String contentType;
        byte[] bytes;
        try {
            contentType = Files.probeContentType(file.toPath());
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Файл не загружен", e);
        }
        String type = contentType.replace("/", "&");
        return DataFileDto.builder()
                .name(file.getName())
                .contentType(type)
                .isEmpty(file.length() == 0)
                .size(file.length())
                .content(bytes)
                .build();
    }
}
