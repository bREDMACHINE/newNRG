package get.a.big.head.newNRG.type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import get.a.big.head.newNRG.users.dtos.UserFullDto;

import java.util.List;

public class TypeMapper {
    public static List<Type> toTypes(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<Type>>(){}.getType());
    }

    public static Type toType(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), Type.class);
    }
}
