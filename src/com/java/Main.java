package com.java;


import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ComplexGsonParse.init(new ComplexGsonParse.InitBuilder()
                .addDeserialize(new MyABCPares())
                .Builder());
        String json = "[{\"type\":\"A\",\"message\":\"这是a00000000000000\"},{\"type\":\"A\",\"message\":\"这是a\"},{\"type\":\"B\",\"message\":\"这是B\"},{\"type\":\"C\",\"message\":\"这是c\",\"data\":\"sssss\"}]";

        List<Object>objects=ComplexGsonParse.parseObjectList(json,new TypeToken<List<Object>>(){}.getType());
        for (Object object : objects) {
            if(object instanceof A){
                System.out.println(((A) object).type+((A) object).message);
            }
        }
    }

    public static class A {
        String type = "";
        String message = "";

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    public static class B {
        String type = "";
        String message = "";

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    public static class C {
        String type = "";
        String message = "";
        String data="";

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
