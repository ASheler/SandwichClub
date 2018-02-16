package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView originTv;
    TextView alsoKnownTv;
    TextView descriptionTv;
    TextView ingredientsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        originTv = findViewById(R.id.origin_tv);
        alsoKnownTv = findViewById(R.id.also_known_tv);
        descriptionTv = findViewById(R.id.description_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSONException ","exceptions"+e);
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //Build Also Known As String
        StringBuilder alsoKnownString = new StringBuilder();
        if (!sandwich.getAlsoKnownAs().isEmpty()) {
            for (String temp : sandwich.getAlsoKnownAs()) {
                alsoKnownString.append(temp).append(", ");
            }
        } else alsoKnownString.append("EMPTY BIATCH");
        //Build Ingredients String
        StringBuilder ingredientsString = new StringBuilder();
        for (String temp : sandwich.getIngredients()) {
            ingredientsString.append(temp).append("\n");
        }

        String originString = !sandwich.getPlaceOfOrigin().isEmpty() ? sandwich.getPlaceOfOrigin() : "EMPTY";

        originTv.setText(originString);
        alsoKnownTv.setText(alsoKnownString);
        descriptionTv.setText(sandwich.getDescription());
        ingredientsTv.setText(ingredientsString);
    }
}
