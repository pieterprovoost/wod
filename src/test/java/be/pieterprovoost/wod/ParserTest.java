package be.pieterprovoost.wod;

import be.pieterprovoost.wod.model.Cast;
import org.junit.Test;

import java.io.InputStream;

public class ParserTest {

    @Test
    public void testParser() {

        InputStream inputStream = this.getClass().getResourceAsStream("OSDO1934");
        Parser parser = new Parser(inputStream);
        Cast cast = parser.parse();
        System.out.println(cast);

    }

}
