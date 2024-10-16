package org.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonReponse = EntityUtils.toString(response.getEntity());

                List<User> users = JsonParser.parseUser(jsonReponse);
                List<User> filteredUsers = users.stream()
                        .filter(user -> user.getUpvotes() != null && user.getUpvotes() > 0)
                        .collect(Collectors.toList());


                filteredUsers.forEach(user ->
                        System.out.println("ID: " + user.getId() +
                                ", text: " + user.getText() +
                                ", type: " + user.getType() +
                                ", user " + user.getUser() +
                                ", upvotes: " + user.getUpvotes())
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}