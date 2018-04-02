package livelib.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.collections4.ListUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiClient {

    private static final String HOST = "www.livelib.ru";
    private static final String PATH = "apiapp/genre";
    private static final String SCHEME = "http";
    private static final String HEADER_USER_AGENT = "User-agent";
    private static final String HEADER_USER_AGENT_VALUE = "LiveLib/3.2.3/15030203 (Redmi 4X; Android 7.1.2; API 25)";
    private static final String HEADER_ACCEPT_ENCODING = "accept-encoding";
    private static final String HEADER_ACCEPT_ENCODING_VALUE = "gzip";
    private static final String PARAM_NAME_KEY = "name";
    private static final String PARAM_START_KEY = "start";
    private static final String PARAM_BASE_KEY = "andyll";
    private static final String PARAM_BASE_VALUE = "and7mpp4ss";
    private static final String GSON_DATA = "data";
    private static final Logger LOGGER = LogManager.getLogger(ApiClient.class);

    private String retrieveGsonBooksByGenreFromPage(String genre, int page) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = new HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegments(PATH)
                .addQueryParameter(PARAM_NAME_KEY, genre)
                .addQueryParameter(PARAM_START_KEY, String.valueOf(page))
                .addQueryParameter(PARAM_BASE_KEY, PARAM_BASE_VALUE)
                .build();
        Request request = new Request.Builder().url(httpUrl).get()
                .addHeader(HEADER_USER_AGENT, HEADER_USER_AGENT_VALUE)
                .addHeader(HEADER_ACCEPT_ENCODING, HEADER_ACCEPT_ENCODING_VALUE)
                .build();
        Response response;
        String jsonString = "";
        try {
            response = client.newCall(request).execute();
            LOGGER.debug("Request " + request.toString() + " was executed");
            jsonString = response.body().string();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return jsonString;
    }

    private List<Book> parseGsonToObjects(String jsonString) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(jsonString);
        Type listType = new TypeToken<List<Book>>() {}.getType();
        JsonElement jsonElement = null;
        if (!(element instanceof JsonNull)) {
            jsonElement = element.getAsJsonObject().get(GSON_DATA);
        }
        List<Book> listBooks = ListUtils.emptyIfNull(new Gson().fromJson(jsonElement, listType));
        LOGGER.debug("Json was parsed to objects");
        return listBooks;
    }

    public ArrayList<Book> getBooksByGenre(String genre) {
        int page = 1;
        ArrayList<Book> allBooks = new ArrayList<>();
        List<Book> allBooksFromPage;
        do {
            String jsonString = retrieveGsonBooksByGenreFromPage(genre, page++);
            LOGGER.debug("Getting books by " + genre + " from page " + page);
            allBooksFromPage = parseGsonToObjects(jsonString);
            allBooks.addAll(allBooksFromPage);
            LOGGER.debug("Books were received from current page");
        } while (allBooksFromPage.size() > 0);
        return allBooks;
    }
}
