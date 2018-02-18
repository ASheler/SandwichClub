package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_MAINNAME_KEY = "mainName";
    public static final String JSON_ALSOKNOWNAS_KEY = "alsoKnownAs";
    public static final String JSON_PLACEOFORIGIN_KEY = "placeOfOrigin";
    public static final String JSON_DESCRIPTION_KEY = "description";
    public static final String JSON_IMAGE_KEY = "image";
    public static final String JSON_INGREDIENTS_KEY = "ingredients";


    public static Sandwich parseSandwichJson(String json) throws JSONException {



        //decode json here
        JSONObject jsonRoot = new JSONObject(json);
        JSONObject name = jsonRoot.optJSONObject(JSON_NAME_KEY);
        String mainName = name.optString(JSON_MAINNAME_KEY);
        //JSON Array for alsoKnownAs Array
        JSONArray alsoKnownAsJSONArray = name.optJSONArray(JSON_ALSOKNOWNAS_KEY);
        List<String> alsoKnownAs = new ArrayList<>();
        for (int i = 0; i < alsoKnownAsJSONArray.length(); i++) {
            String val = alsoKnownAsJSONArray.optString(i);
            alsoKnownAs.add(val);
        }
        String placeOfOrigin = jsonRoot.optString(JSON_PLACEOFORIGIN_KEY);
        String description = jsonRoot.optString(JSON_DESCRIPTION_KEY);
        String image = jsonRoot.optString(JSON_IMAGE_KEY);
        //JSON Array for Ingredients
        JSONArray ingredientsJSONArray = jsonRoot.optJSONArray(JSON_INGREDIENTS_KEY);
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsJSONArray.length(); i++) {
            String val = ingredientsJSONArray.optString(i);
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
