package com.java;

import com.google.gson.*;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexGsonParse {
    private static Gson gson;

    public static <T>T parseObjectList(String toParse, Type type) {
        return gson.fromJson(toParse, type);
    }
    public static <T>T parseObjectList(String toParse, Class<T> type) {
        return gson.fromJson(toParse, type);
    }

    public static void init(HashMap<Type, ComplexParseDeserialize> types) {
        GsonBuilder gsonb = new GsonBuilder();

        for (Map.Entry<Type, ComplexParseDeserialize> typeJsonDeserializerEntry : types.entrySet()) {
            Type key = typeJsonDeserializerEntry.getKey();
            JsonDeserializer value = typeJsonDeserializerEntry.getValue();
            gsonb.registerTypeAdapter(key, value);
        }
        gsonb.serializeNulls();
        gson = gsonb.create();
    }

    public static class InitBuilder {
        private HashMap<Type, ComplexParseDeserialize> types = new HashMap<>();
        public InitBuilder addDeserialize( ComplexParseDeserialize deserialize) {
            types.put(deserialize.getType(), deserialize);
            return this;
        }

        public HashMap<Type, ComplexParseDeserialize> Builder() {
            return types;
        }

    }

    public static Gson get() {
        return gson;
    }

    public static abstract class ComplexParseDeserialize<T> implements JsonDeserializer<T> {
        public abstract Type getType();
    }
}
