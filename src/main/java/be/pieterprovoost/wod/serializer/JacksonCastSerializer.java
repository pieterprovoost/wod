package be.pieterprovoost.wod.serializer;

import be.pieterprovoost.wod.model.Cast;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonCastSerializer implements CastSerializer {

    public String serialize(Cast cast, boolean pretty) {

        String result = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            if (pretty) {
                result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cast);
            } else {
                result = mapper.writer().writeValueAsString(cast);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

}
