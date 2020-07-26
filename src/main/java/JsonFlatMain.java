import org.json.simple.JSONObject;

public class JsonFlatMain {
    public static JSONObject readJson() {
        return new JSONObject();
    }

    public static JSONObject flatJson(JSONObject jo) {
        return new JSONObject();
    }

    public static void main(String[] args) {
        JSONObject jo = readJson();
        JSONObject flatten = flatJson(jo);
        System.out.println(flatten);
    }
}
