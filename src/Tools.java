import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static java.lang.String.format;

public class Tools {
    public static JsonElement getJson(String url) throws IOException {
        URL location = new URL(url);
        InputStreamReader reader = new InputStreamReader(location.openStream());
        return JsonParser.parseReader(reader);
    }

    public static JsonElement get(JsonElement root, Object... keys ) {
        for (Object key : keys) {
            try {
                if (key instanceof String) {
                    root = root.getAsJsonObject().get((String) key);
                } else if (key instanceof Integer) {
                    root = root.getAsJsonArray().get((Integer) key);
                } else {
                    String message = format("argument type not supported (%s)", key.getClass());
                    throw new IllegalArgumentException(message);
                }
            } catch (NullPointerException npe) {
                String message = format("key does not exist (%s)", key);
                throw new NullPointerException(message);
            }
        }
        return root;
    }
}
