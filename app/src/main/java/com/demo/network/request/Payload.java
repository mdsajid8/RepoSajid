package com.demo.network.request;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Just a wrapper around JSONObject.
 * <p/>
 * Kept around to avoid code rewrite
 * and the convenience of chaining and Exception handling while adding data
 * <p/>
 */
public class Payload extends JSONObject {

    private static final String TAG = Payload.class.getSimpleName();

    public Payload() {
        super();
    }

    public Payload(String json) throws JSONException {
        super(json);
    }

    public Payload add(String key, Object value) {
        try {
            super.put(key, value);
        } catch (JSONException ignore) {
        }
        return this;
    }

    public Payload addAll(HashMap<String, String> params) {
        for (Map.Entry<String, String> keyValPair : params.entrySet()) {
            add(keyValPair.getKey(), keyValPair.getValue());
        }
        return this;
    }

    public Payload addAll(Bundle params) {
        for (String key : params.keySet()) {
            add(key, params.get(key).toString()); //To Implement
        }
//        for (Map.Entry<String, String> keyValPair : params.entrySet()) {
//            add(keyValPair.getKey(), keyValPair.getValue());
//        }
        return this;
    }

    /**
     * add all keys of JsonObject to payload
     *
     * @param jsonObject
     * @return
     */
    public Payload addAll(JSONObject jsonObject) {
        if (jsonObject != null) {
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                try {
                    add(key, jsonObject.get(key));
                } catch (Exception e) {
//                    Log.e(TAG, "JSONException", e);
                }
            }
        }
        return this;
    }


    public Bundle toBundle() {
        Bundle map = new Bundle();

        Iterator<String> keysItr = this.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            try {
                String value = this.getString(key);
                map.putString(key, value);
            } catch (JSONException e) {
//                Log.e(TAG, "JSONException", e);
            }
        }
        return map;
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> map = new HashMap<String, String>();

        Iterator<String> keysItr = this.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            try {
                String value = this.getString(key);
                map.put(key, value);
            } catch (JSONException e) {
//                Log.e(TAG, "JSONException", e);
            }
        }
        return map;
    }

    public static String getJsonString(Payload payload) {
        if (payload == null || payload.length() == 0)
            return "";

        return payload.toString();
    }
}
