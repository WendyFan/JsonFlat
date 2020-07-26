import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JsonFlatMainTest {

    @Test
    public void testFlatJson() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testdata/input.txt").getFile());
        String st = JsonFlatMain.readInput(new FileInputStream(file));
        String expected = "{    \"a\": 1,    \"b\": true,    \"c\": {        \"d\": 3,        \"e\": \"test\"    }}";
        Assert.assertTrue(st.equals(expected));
    }
}


