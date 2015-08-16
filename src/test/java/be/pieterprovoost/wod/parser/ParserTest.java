package be.pieterprovoost.wod.parser;

import be.pieterprovoost.wod.model.Cast;
import be.pieterprovoost.wod.serializer.JacksonCastSerializer;
import org.junit.Test;

import java.io.InputStream;

public class ParserTest {

    @Test
    public void testParser() {

        InputStream inputStream = this.getClass().getResourceAsStream("67064");
        Parser parser = new Parser(inputStream);
        Cast cast = parser.parse();

        Decoder.decode(cast);

        System.out.println(new JacksonCastSerializer().serialize(cast, true));

    }

}
