package ru.bda.itunes.controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ru.bda.itunes.model.SearchAgent;

/**
 * Created by User on 16.09.2016.
 */
public class ResponseParser {
    private static volatile ResponseParser instance;

    private ResponseParser(){}


    public static ResponseParser getInstance(){
        if(instance == null){
            synchronized (ApiController.class) {
                if(instance == null){
                    instance = new ResponseParser();
                }
            }
        }
        return instance;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public List<SearchAgent> parseAagent(InputStream inputStream) {
        List<SearchAgent> agentList = new ArrayList<>();
        String response = convertStreamToString(inputStream);
        JSONObject json;
        try {
            json = new JSONObject(response);
            JSONArray array = json.getJSONArray("results");
            if (array != null) {
                SearchAgent agent = null;
                for (int i = 0; i < array.length(); i++) {
                    agent = new SearchAgent();
                    agent.setName(array.getJSONObject(i).getString("artistName"));
                    agent.setDescription(array.getJSONObject(i).getString("trackName"));
                    agent.setUrlImage(array.getJSONObject(i).getString("artworkUrl100"));
                    agentList.add(agent);
                }
            }

        } catch (JSONException e) {
            return null;
        }
        return agentList;
    }
}
