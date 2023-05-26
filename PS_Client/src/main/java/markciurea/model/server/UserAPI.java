package markciurea.model.server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import markciurea.model.entities.dto.UserShort;
import markciurea.model.entities.user.User;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class UserAPI {

    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final String BASE_URL = "http://localhost:8080";
    private static final MediaType JSON = MediaType.parse("application/json");
    private static final Gson gsonParser = new Gson();

    public static User logIn(String username, String password) {
        JsonObject body = new JsonObject();
        body.addProperty("username", username);
        body.addProperty("password", password);

        Request request = new Request.Builder()
                .url(BASE_URL + "/user/login")
                .post(RequestBody.create(JSON, gsonParser.toJson(body)))
                .build();

        return executeUserRequest(request);
    }

    public static void createUser(UserShort newUser) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("user")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON, gsonParser.toJson(newUser)))
                .build();

        executeUserRequest(request);
    }

    public static List<User> getAllUsers() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("user")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gsonParser.fromJson(
                        Objects.requireNonNull(response.body()).string(),
                        TypeToken.getParameterized(List.class, User.class).getType());
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUserById(Long id) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("user")
                .addPathSegment(String.valueOf(id))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        executeUserRequest(request);
    }

    private static User executeUserRequest(Request request) {
        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return gsonParser.fromJson(Objects.requireNonNull(response.body()).string(), User.class);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserById(Long id) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("user")
                .addPathSegment(String.valueOf(id))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return executeUserRequest(request);
    }

    public static User updateUser(UserShort userShort) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("user")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .patch(RequestBody.create(JSON, gsonParser.toJson(userShort)))
                .build();

        return executeUserRequest(request);
    }
}
