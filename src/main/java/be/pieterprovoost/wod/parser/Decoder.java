package be.pieterprovoost.wod.parser;

import be.pieterprovoost.wod.model.Coded;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Decoder {

    final static Logger logger = Logger.getLogger(Parser.class);
    private JsonObject tables;

    public Decoder() {

        try {
            InputStream inputStream = this.getClass().getResourceAsStream("code.json");
            String json = IOUtils.toString(inputStream, "UTF-8");
            Gson gson = new Gson();
            tables = gson.fromJson(json, JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setProperties(Object o) {

        String table = o.getClass().getSimpleName();
        String code = ((Coded) o).getCode().toString();

        if (tables.has(table)) {
            JsonObject t = tables.get(table).getAsJsonObject();
            if (t.has(code)) {

                JsonObject properties = t.get(code).getAsJsonObject();

                for (Map.Entry<String, JsonElement> entry : properties.entrySet()) {
                    try {
                        Field field = o.getClass().getDeclaredField(entry.getKey());
                        field.setAccessible(true);
                        field.set(o, entry.getValue().getAsString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public void decode(Object o) {

        if (Coded.class.isAssignableFrom(o.getClass())) {
            Coded coded = (Coded) o;
            setProperties(o);
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