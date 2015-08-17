package be.pieterprovoost.wod.parser;

import org.junit.Test;

import java.io.InputStream;

public class ParserTest {

    @Test
    public void testParser() {

        InputStream inputStream = this.getClass().getResourceAsStream("67064");
        Parser parser = new Parser(inputStream);
        parser.drop();
        parser.parse();

    }

}
