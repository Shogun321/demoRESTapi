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
    private static String logincredentials;//username . password
    public Sender() {}
    private void update() {
        try{
            // form parameters
            Map<Object, Object> data = new HashMap<>();
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://skupinska.c1.biz/tasks"))
                    .setHeader("Content-Type", "text/json")
                    .setHeader("auth-token", logincredentials)
                    .build();
            //response updates here
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void newtask(String jsondata) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://skupinska.c1.biz/add_task"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsondata))
                    .setHeader("Content-Type", "text/json; utf-8")
                    .setHeader("auth-token", logincredentials)
                    .build();
            System.out.println("Ispisujem jsondata: "+jsondata);
            //response updates here
            System.out.println();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Ispisujem response:");
            System.out.println(response.body());
            System.out.println(response.request());
            System.out.println(response.headers());
            System.out.println(response.previousResponse());
            // print status code from server
            System.out.println(response.statusCode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void delete(String jsondata){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://skupinska.c1.biz/delete_task"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsondata))
                    .setHeader("Content-Type", "text/json; utf-8")
                    .setHeader("auth-token", logincredentials)
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void edit(String jsondata) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://skupinska.c1.biz/edit_task"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsondata))
                    .setHeader("Content-Type", "text/json; utf-8")
                    .setHeader("auth-token", logincredentials)
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int login(String newLogininformation){
        int error = 0;
        try{
            logincredentials = getMd5(newLogininformation);
            Map<Object, Object> data = new HashMap<>();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://skupinska.c1.biz/login"))
                    .POST(buildFormDataFromMap(data))
                    .setHeader("Content-Type", "text/json; utf-8")
                    .setHeader("auth-token", logincredentials)
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject responseasjson = new JSONObject(response.body());
            error= (int) responseasjson.get("error_id");
            System.out.println("Ispisujem response:");
            System.out.println(response.body());
            System.out.println(response.request());
            System.out.println(response.headers());
            System.out.println(response.previousResponse());
            // print status code from server
            System.out.println(response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        update();
        return error;
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
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    private static HttpRequest.BodyPublisher buildFormDataFromMapstring(Map<String,String> data) {
        var builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    //takes string and turns into JSON object
    public JSONArray parseJSON(){
        JSONArray albums = new JSONArray(response.body());

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
    public static String getLogincredentials() {
        return logincredentials;
    }

    public void setLogincredentials(String logincredentials) {
        Sender.logincredentials = getMd5(logincredentials);
    }


}
