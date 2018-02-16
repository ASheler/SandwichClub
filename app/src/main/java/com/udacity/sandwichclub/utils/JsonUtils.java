package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        //decode json here
        JSONObject jsonRoot = new JSONObject(json);
        JSONObject name = jsonRoot.getJSONObject("name");
        String mainName = name.getString("mainName");
        //JSON Array for alsoKnownAs Array
        JSONArray alsoKnownAsJSONArray = name.getJSONArray("alsoKnownAs");
        List<String> alsoKnownAs = new ArrayList<>();
        for (int i = 0; i < alsoKnownAsJSONArray.length(); i++) {
            String val = alsoKnownAsJSONArray.getString(i);
            alsoKnownAs.add(val);
        }
        String placeOfOrigin = jsonRoot.getString("placeOfOrigin");
        String description = jsonRoot.getString("description");
        String image = jsonRoot.getString("image");
        //JSON Array for Ingredients
        JSONArray ingredientsJSONArray = jsonRoot.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsJSONArray.length(); i++) {
            String val = ingredientsJSONArray.getString(i);
            ingredients.add(val);
        }

        //build and return Sandwich

        return new Sandwich(
                mainName,
                alsoKnownAs,
                placeOfOrigin,
                description,
                image,
                ingredients
        );
    }
}
