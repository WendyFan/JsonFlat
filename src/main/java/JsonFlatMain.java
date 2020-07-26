import jdk.incubator.jpackage.internal.Log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.Charset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
            Log.error("readInput: Failed to read buffer " + e.toString());
            return "";
        }
        return sb.toString();
    }

    public static JSONObject parseJson(String st) {
        try {
            return (JSONObject) new JSONParser().parse(st);
        } catch (Exception e) {
            Log.error("parseJson: Failed to parse string " + st);
            return new JSONObject();
        }
    }

    public static JSONObject flatJson(JSONObject jo) {
        return jo;
    }

    public static void main(String[] args) {
        String input = readInput(System.in);
        JSONObject jo = parseJson(input);
        JSONObject flatten = flatJson(jo);
        System.out.println(flatten);
    }
}
