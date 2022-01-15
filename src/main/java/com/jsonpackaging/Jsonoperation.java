package com.jsonpackaging;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Jsonoperation {
    private static JSONArray albums;
    private int ID;
    private String name, description, task_date;
    public Jsonoperation() {
        System.out.println("Usao sam u Jsonoperation");

        /*for (int i = 0; i < albums.length(); i++) {
            JSONObject album=albums.getJSONObject(i);
            System.out.println("Evo album objekat:id:"+album.get("id")+" name:"+album.get("name")+" description:"
                    +album.get("description")+" task_date:"+album.get("task_date"));
        }*/
        System.out.println("Izlazim iz JSONoperation");
    }
    public void setJSONArray(JSONArray array){
        albums=array;
    }
    public String newJsonObject(HashMap<String, String> maptoJson){
        JSONObject item = new JSONObject(maptoJson);
        return item.toString();
    }

    public JSONArray getAlbums() {
        return albums;
    }

    public void setAlbums(JSONArray albums) {
        Jsonoperation.albums = albums;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask_date() {
        return task_date;
    }

    public void setTask_date(String task_date) {
        this.task_date = task_date;
    }
}
