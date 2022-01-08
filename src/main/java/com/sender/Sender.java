package com.sender;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Sender {
    // one instance, reuse
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    private static HttpResponse<String> response;
    public Sender() {
        /*HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.github.com/users/defunkt")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).thenApply(Sender::parse).join();*/
        System.out.println("Testing 2 - Send Http POST request");
        try {//update load data once/first
            update();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private void update() throws Exception {
        // form parameters
        Map<Object, Object> data = new HashMap<>();
        String auth=getMd5("bastrijan.111");//username . password
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create("http://skupinska.c1.biz/tasks"))
                .setHeader("Content-Type", "text/json")
                .setHeader("auth-token", auth)
                .build();
        //response updates here
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // print status code from server
        System.out.println(response.statusCode());
    }
    public void newtask(String logincredentials,JSONObject jsondata) {
        try{
            Map<Object, Object> data = new HashMap<>();

            String auth=getMd5(logincredentials);//username . password
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://skupinska.c1.biz/tasks"))
                    .setHeader("Content-Type", "text/json")
                    .setHeader("auth-token", auth)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //take object from post and turns to string
    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println("evo print out");
        System.out.println(builder.toString());
        System.out.println(HttpRequest.BodyPublishers.ofString(builder.toString()));
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    //takes string and turns into JSON object
    public JSONArray parseJSON(){
        JSONArray albums = new JSONArray(response.body());
        /*for (int i = 0; i < albums.length(); i++) {
            JSONObject album=albums.getJSONObject(i);
            System.out.println("Evo album objekat:id:"+album.get("id")+" name:"+album.get("name")+" description:"
                    +album.get("description")+" task_date:"+album.get("task_date"));
        }
        System.out.println("Evo novi red");*/
        return albums;
    }
    //turns string to MD5 bullshit
    public static String getMd5(String input)
    {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
