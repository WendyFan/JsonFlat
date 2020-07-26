import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JsonFlatMainTest {
    final String jsonInString = "{    \"a\": 1,    \"b\": true,    \"c\": {        \"d\": 3,        \"e\": \"test\"    }}";

    @Test
    public void testReadJsonFile() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testdata/input.txt").getFile());
        String st = JsonFlatMain.readInput(new FileInputStream(file));
        Assert.assertTrue(st.equals(jsonInString));
    }

    @Test
    public void testParseJson() {
        Assert.assertTrue(jsonInString.equals(JsonFlatMain.flatJson(jsonInString)));
    }
}


