package get.a.big.head.newNRG.files;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import get.a.big.head.newNRG.exception.NotFoundException;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileMapper {

    public static DataFile toDataFile(Object object) {
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
            System.out.println(jsonObject.get("content"));
            byte[] content = DatatypeConverter.parseBase64Binary(jsonObject.get("content").getAsString());

            return DataFile.builder()
                    .fileId(fileId)
                    .name(name)
                    .contentType(type)
                    .isEmpty(isEmpty)
                    .size(size)
                    .content(content)
                    .build();
        }
        return DataFile.builder()
                .fileId(fileId)
                .name(name)
                .contentType(type)
                .isEmpty(isEmpty)
                .size(size)
                .build();
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(byte[].class, new ByteArrayAdapter())
//                .create();
////        try {
//            JsonReader jsonReader = new JsonReader(new StringReader(object.toString()));
////            jsonReader.close();
//            return gson.fromJson(jsonReader, DataFile.class);
////        } catch (IOException e) {
////            throw new NotFoundException("Файл не загружен");
////        }
    }

    public static DataFile toDataFile(File file) {
        String contentType;
        byte[] bytes;
        try {
            contentType = Files.probeContentType(file.toPath());
            bytes = Files.readAllBytes(file.toPath());
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

//    static class ByteArrayAdapter extends TypeAdapter<byte[]> {
//
//        @Override
//        public void write(final JsonWriter jsonWriter, final byte[] bytes) throws IOException {
//            new JsonPrimitive(Base64.getEncoder().encodeToString(bytes));
//        }
//
//        @Override
//        public byte[] read(final JsonReader reader) {
//            reader.setLenient(true);
//            try {
////                StringBuilder builder = new StringBuilder();
////                while (reader.hasNext()) {
//                    switch (reader.peek()) {
//
//                        case BEGIN_ARRAY:
//                            reader.beginArray();
//                            break;
//                        case STRING:
//                            String s = reader.toString();
//                            System.out.println(s);
//                            return DatatypeConverter.parseBase64Binary(s);
//
//
//                        case END_ARRAY:
//                            reader.endArray();
//                            break;
//                    }
////                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        public byte[] append(byte[] a1, byte[] a2) {
//            byte[] a1a2 = Arrays.copyOf(a1, a1.length + a2.length);
//            for (int i = a1.length; i < a1a2.length; i++) {
//                a1a2[i] = a2[i - a1.length];
//            }
//            return a1a2;
//        }
//    }
}
