package be.pieterprovoost.wod.parser;

import be.pieterprovoost.wod.model.Coded;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

public class Decoder {

    final static Logger logger = Logger.getLogger(Parser.class);

    public Decoder() {

        try {
            InputStream inputStream = this.getClass().getResourceAsStream("code.json");
            String json = IOUtils.toString(inputStream, "UTF-8");
            Gson gson = new Gson();
            JsonArray codes = gson.fromJson(json, JsonArray.class);

            logger.info(codes);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void decode(Object o) {

        if (Coded.class.isAssignableFrom(o.getClass())) {
            Coded coded = (Coded) o;

            // to be implemented

        }

        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (field.getType().getPackage().getName().startsWith("be.pieterprovoost.wod")) {

                try {
                    field.setAccessible(true);
                    decode(field.get(o));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (List.class.isAssignableFrom(field.getType())) {

                try {
                    field.setAccessible(true);
                    Object child = field.get(o);
                    for (Object item : (List) child) {
                        decode(item);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

}