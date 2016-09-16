package ru.bda.itunes.controller;

import android.content.Context;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ru.bda.itunes.model.SearchAgent;

/**
 * Created by User on 16.09.2016.
 */
public class ApiController {
    private final String mBaseUrl = "https://itunes.apple.com/search?term=";
    private static volatile ApiController instance;
    private Context context;

    private ApiController(){}

    public static ApiController getInstance() {
        if (instance == null) {
            synchronized (ApiController.class) {
                if (instance == null) {
                    instance = new ApiController();
                }
            }
        }
        return instance;
    }

    public List<SearchAgent> getSearchAgent(String name) {
        String urlString = mBaseUrl + name;
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.connect();
            return ResponseParser.getInstance().parseAagent(connection.getInputStream());
        } catch (Exception e) {
            return null;
        }
    }

}
