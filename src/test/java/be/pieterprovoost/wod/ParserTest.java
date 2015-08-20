package be.pieterprovoost.wod;

import com.mongodb.BasicDBObject;
import org.junit.Test;

import java.io.InputStream;

public class ParserTest {

    @Test
    public void testParser() {

        InputStream inputStream = this.getClass().getResourceAsStream("67064");
        Parser parser = new Parser(inputStream);
        BasicDBObject cast = parser.parse();

        Repository repository = new Repository();
        repository.drop();
        repository.save(cast);

    }

}