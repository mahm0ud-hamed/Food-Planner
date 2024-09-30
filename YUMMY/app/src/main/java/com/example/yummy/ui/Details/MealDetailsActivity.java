package com.example.yummy.ui.Details;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.yummy.Model.MealDetails;
import com.example.yummy.R;

import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements IMealDetail{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

    }

    @Override
    public void viewMealDetails(List<MealDetails> mealDetails) {
        // pass to adapter
    }
}