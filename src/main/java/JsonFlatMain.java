import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

public class JsonFlatMain {
    public static String readInput(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.defaultCharset()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }

    public static String flatJson(String in) {
        JsonObject result = new JsonObject();
        JsonElement je = new JsonParser().parse(in);
        flatJsonInternal("", je.getAsJsonObject(), result);
        return result.toString();
    }

    private static void flatJsonInternal(String prefix, JsonObject jo, JsonObject result) {
        for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
            String key = concatenateWithPrefix(prefix, entry.getKey());
            JsonElement je = entry.getValue();
            if (je.isJsonPrimitive()) {
                result.add(key, je);
            } else if (je.isJsonObject()) {
                flatJsonInternal(key, je.getAsJsonObject(), result);
            } else {
                throw new RuntimeException("Unexpected input " + jo);
            }
        }
    }

    private static String concatenateWithPrefix(String prefix, String key) {
        if (prefix.isEmpty()) {
            return key;
        } else {
            return prefix + "." + key;
        }
    }

    public static void main(String[] args) {
        String input = readInput(System.in);
        String flatten = flatJson(input);
        System.out.println(flatten);
    }
}
