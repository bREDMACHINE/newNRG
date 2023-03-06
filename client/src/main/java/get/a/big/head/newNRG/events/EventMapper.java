package get.a.big.head.newNRG.events;

import com.google.gson.Gson;

public class EventMapper {

    public static Event toEvent(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), Event.class);
    }
}
