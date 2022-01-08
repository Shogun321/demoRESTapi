package com.jsonpackaging;

import org.json.JSONArray;
import org.json.JSONObject;

public class Jsonoperation {
    private static JSONArray albums;
    public Jsonoperation(JSONArray array) {
        System.out.println("Usao sam u Jsonoperation");
        albums=array;
        for (int i = 0; i < albums.length(); i++) {
            JSONObject album=albums.getJSONObject(i);
            System.out.println("Evo album objekat:id:"+album.get("id")+" name:"+album.get("name")+" description:"
                    +album.get("description")+" task_date:"+album.get("task_date"));
        }
        System.out.println("Izlazim iz JSONoperation");
    }
    public JSONObject newJson(){
        JSONObject item = new JSONObject();
        item.put("name", "test");
        item.put("description", "course1 ladida");

        return item;
    }
}
