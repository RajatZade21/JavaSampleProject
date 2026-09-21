package org.ProblemSolving;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

class MediaItem {
    String imdbId;
    Integer tmdbId;
    Integer rating;
    String type; // movie/show
    boolean watched;
}

public class SampleTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        String CLIENT_ID = "d8f7fc1b53e4d4be06064088d8bb6f09d1ddde62aa85aa43daf329638324c26f";
        String clientSecret =  "71fb5fc4c84b22e56528a4956f7434c1ef21bafbd154cd1132de46651686ae7a";

        String body = """
{
  "code": "%s",
  "client_id": "%s",
  "client_secret": "%s",
  "redirect_uri": "urn:ietf:wg:oauth:2.0:oob",
  "grant_type": "authorization_code"
}
""".formatted("5886d929", CLIENT_ID, clientSecret);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.trakt.tv/oauth/token"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        String responseBody = response.body();

// Extract access_token
        String accessToken = responseBody
                .split("\"access_token\"\\s*:\\s*\"")[1]
                .split("\"")[0];

// Extract refresh_token
        String refreshToken = responseBody
                .split("\"refresh_token\"\\s*:\\s*\"")[1]
                .split("\"")[0];

        int page = 1;
        List<MediaItem> batch = new ArrayList<>();
        while (true) {
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://api.trakt.tv/users/me/ratings/movies"))
                .header("Content-Type", "application/json")
                .header("trakt-api-version", "2")
                .header("trakt-api-key", CLIENT_ID)
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();
            HttpResponse<String> response2 = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response2.body().equals("[]")) break;

            // process data

            page++;
        }
    }
}


