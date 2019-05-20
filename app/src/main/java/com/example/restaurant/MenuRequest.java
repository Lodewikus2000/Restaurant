package com.example.restaurant;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback currentActivity;


    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }


    // Constructor
    public MenuRequest(Context aContext) {
        context = aContext;

    }



    @Override
    public void onErrorResponse(VolleyError error) {

        String errorMessage = error.getMessage();
        currentActivity.gotMenuItemsError(errorMessage);

    }


    @Override
    public void onResponse(JSONObject response) {

        // Empty list.
        ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

        try {
            JSONArray menuItemsArray = (JSONArray) response.getJSONArray("items");


            for (int i = 0; i < menuItemsArray.length(); i++) {


                JSONObject menuJson = menuItemsArray.getJSONObject(i);
                String name = menuJson.getString("name");
                String description = menuJson.getString("description");
                String imageURL = menuJson.getString("image_url");
                int price = menuJson.getInt("price");
                String category = menuJson.getString("category");

                MenuItem item = new MenuItem(name, description, imageURL, price, category);

                menuItems.add(item);



            }

            currentActivity.gotMenuItems(menuItems);


        } catch (JSONException exception) {
            exception.printStackTrace();
        }

    }



    public void getMenuItems(Callback activity, String category) {

        currentActivity= activity;

        RequestQueue queue = Volley.newRequestQueue(context);

        // The API delivers its data as a Json.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category=" + category, null, this, this);
        queue.add(jsonObjectRequest);

    }
}
