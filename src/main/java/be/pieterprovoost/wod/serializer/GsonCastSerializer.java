package be.pieterprovoost.wod.serializer;

import be.pieterprovoost.wod.model.Cast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonCastSerializer implements CastSerializer {

    public String serialize(Cast cast, boolean pretty) {

        GsonBuilder builder = new GsonBuilder();

        if (pretty) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();
        return gson.toJson(cast);

    }

}