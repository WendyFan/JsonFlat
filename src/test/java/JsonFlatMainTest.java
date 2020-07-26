import com.google.gson.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;

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
        JsonElement je = new JsonParser().parse(jsonInString);
        JsonObject jobject = je.getAsJsonObject();
        Set<String> set = jobject.keySet();
        Assert.assertEquals(set.size(), 3);
        Assert.assertTrue(jobject.get("a").getAsJsonPrimitive().isNumber());
        Assert.assertEquals(jobject.get("a").getAsString(), "1");
        Assert.assertTrue(jobject.get("b").getAsJsonPrimitive().isBoolean());
        Assert.assertEquals(jobject.get("b").getAsString(), "true");
        Assert.assertTrue(jobject.get("c").getAsJsonObject().get("d").getAsJsonPrimitive().isNumber());
        Assert.assertEquals(jobject.get("c").getAsJsonObject().get("d").getAsString(), "3");
        Assert.assertTrue(jobject.get("c").getAsJsonObject().get("e").getAsJsonPrimitive().isString());
        Assert.assertEquals(jobject.get("c").getAsJsonObject().get("e").getAsString(), "test");
    }

    @Test
    public void testFlatJson() {
        JsonElement je = new JsonParser().parse(JsonFlatMain.flatJson(jsonInString));
        JsonObject jobject = je.getAsJsonObject();
        Set<String> set = jobject.keySet();
        Assert.assertEquals(set.size(), 4);
        Assert.assertTrue(jobject.get("a").getAsJsonPrimitive().isNumber());
        Assert.assertEquals(jobject.get("a").getAsString(), "1");
        Assert.assertTrue(jobject.get("b").getAsJsonPrimitive().isBoolean());
        Assert.assertEquals(jobject.get("b").getAsString(), "true");
        Assert.assertTrue(jobject.get("c.d").getAsJsonPrimitive().isNumber());
        Assert.assertEquals(jobject.get("c.d").getAsString(), "3");
        Assert.assertTrue(jobject.get("c.e").getAsJsonPrimitive().isString());
        Assert.assertEquals(jobject.get("c.e").getAsString(), "test");
    }
}


