package com.example.restaurant;

import android.app.Activity;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback currentActivity;


    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }


    // Constructor
    public CategoriesRequest(Context aContext) {
        context = aContext;
    }



    @Override
    public void onErrorResponse(VolleyError error) {

        String errorMessage = error.getMessage();
        currentActivity.gotCategoriesError(errorMessage);

    }


    @Override
    public void onResponse(JSONObject response) {

        try {

            ArrayList<String> categories = new ArrayList<String>();

            JSONArray categoriesArray = (JSONArray) response.getJSONArray("categories");


            for (int i = 0; i < categoriesArray.length(); i++) {
                String category = null;

                category = categoriesArray.getString(i);

                categories.add(category);
            }

            currentActivity.gotCategories(categories);

        } catch (JSONException exception) {
            exception.printStackTrace();
        }

    }



    public void getCategories(Callback activity) {

        currentActivity= activity;

        RequestQueue queue = Volley.newRequestQueue(context);

        // The API delivers its data as a Json.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);

    }
}
