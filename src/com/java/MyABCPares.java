package com.java;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyABCPares extends ComplexGsonParse.ComplexParseDeserialize<List<Object>> {
    private HashMap<String, Class> types=new HashMap<>();

    public void setTypes(HashMap<String, Class> typesx) {
        types = typesx;
    }

    public MyABCPares() {
        types.put("A",Main.A.class);
        types.put("B",Main.B.class);
        types.put("C",Main.C.class);
    }

    @Override
    public List<Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ListFactory result = null;
        try {
            JsonArray array = json.getAsJsonArray();
            result = new ListFactory();
            for (JsonElement jsonElement : array) {
                try {
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();
                    String typekey = asJsonObject.get("type").getAsString();
                    if (types.containsKey(typekey)) {
                        Object one = ComplexGsonParse.get().fromJson(jsonElement, types.get(typekey));
                        result.add(one);
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    System.out.println(jsonElement.toString() + " 解析错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(json.toString() + " 解析错误");
        }
        return result.get();
    }

    @Override
    public Type getType() {
        return new TypeToken<List<Object>>(){}.getType();
    }

    private static class ListFactory {
        private List<Object> data = new ArrayList<Object>();

        public void add(Object obj) {
            data.add(obj);
        }

        public List<Object> get() {
            return data;
        }
    }

}
