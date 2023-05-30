package markciurea.model.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import markciurea.model.entities.thrashLocation.ThrashLocation;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ThrashLocationAPI {

    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final String BASE_URL = "http://localhost:8080";
    private static final MediaType JSON = MediaType.parse("application/json");
    private static final Gson gsonParser = new Gson();

    public static List<ThrashLocation> getAllThrashLocations() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("thrash")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gsonParser.fromJson(
                        Objects.requireNonNull(response.body()).string(),
                        TypeToken.getParameterized(List.class, ThrashLocation.class).getType()
                );
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static List<ThrashLocation> getAllThrashLocationsForEmail(String email) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("thrash")
                .addEncodedQueryParameter("email", email)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gsonParser.fromJson(
                        Objects.requireNonNull(response.body()).string(),
                        TypeToken.getParameterized(List.class, ThrashLocation.class).getType()
                );
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void saveThrashLocation(ThrashLocation newThrashLocation) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("thrash")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON, gsonParser.toJson(newThrashLocation)))
                .build();

        try {
            httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(Long id) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("thrash")
                .addPathSegment(String.valueOf(id))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try {
            httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
