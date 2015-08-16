package be.pieterprovoost.wod.parser;

import be.pieterprovoost.wod.model.Coded;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.List;

public class Decoder {

    final static Logger logger = Logger.getLogger(Parser.class);

    public static void decode(Object o) {

        if (Coded.class.isAssignableFrom(o.getClass())) {
            Coded coded = (Coded) o;

            // to be implemented

        }

        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (field.getType().getPackage().getName().startsWith("be.pieterprovoost.wod")) {

                try {
                    field.setAccessible(true);
                    Decoder.decode(field.get(o));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (List.class.isAssignableFrom(field.getType())) {

                try {
                    field.setAccessible(true);
                    Object child = field.get(o);
                    for (Object item : (List) child) {
                        Decoder.decode(item);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

}