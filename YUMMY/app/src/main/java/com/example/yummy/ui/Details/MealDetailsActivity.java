package com.example.yummy.ui.Details;

import static com.example.yummy.ui.Favourite.FavouriteFragment.fromFav;
import static com.example.yummy.ui.home.HomeFragment.MealKey;
import static com.example.yummy.ui.weekPlan.weekPlan.fromSrc;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.utils.Utils;
import com.bumptech.glide.Glide;
import com.example.yummy.MealPresenter.LocalDataPresenter;
import com.example.yummy.Model.DataBase.DataBase;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Network.RemoteDataSource;
import com.example.yummy.Model.Pojos.MealPlan;
import com.example.yummy.R;
import com.example.yummy.MealPresenter.RemoteDataPresenter;
import com.example.yummy.ui.Srearch.IngredientAdapter;
import com.example.yummy.utils.Uitlity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements IMealDetail {
    private RemoteDataPresenter remoteDataPresenter;
    private ImageView imgDetMeal;
    private TextView txtMealName;
    private TextView txtMelInstruction;
    private WebView webViewvideo;
    private RecyclerView recyclerView;
    private IngredientAdapter ingredientAdapter;
    private ImageButton btnAddFav;
    private ImageButton btnAddPlan;
    private LocalDataPresenter localDataPresenter;
    private LocalDataPresenter weekPlanPresenter;
    private MealDetails mealtoInsert;
    private Observer<MealDetails> observer;
    private Observer<MealDetails> observerPlan;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        getSupportActionBar().hide();
        /*Receving data from intent */
        Intent intent = getIntent();
        String mealName = intent.getStringExtra(MealKey);

        /*refrence on all UI objects */
        imgDetMeal = findViewById(R.id.imgDetMeal);
        txtMealName = findViewById(R.id.txtDetMealName);
        txtMelInstruction = findViewById(R.id.txtInstruction);
        recyclerView = findViewById(R.id.recycViewDetIngred);
        webViewvideo = findViewById(R.id.webViewVideo);
        btnAddFav = findViewById(R.id.btnAddtFav);
        btnAddPlan = findViewById(R.id.btnAddToPlan);

        /*creating layout manager to show all ingreident over an recycler view */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        /*creaitng instance from data base */
        localDataPresenter = new LocalDataPresenter(DataBase.getInstance(this).getMealDAO());
        weekPlanPresenter = new LocalDataPresenter(DataBase.getInstance(this).getWeekPlanDao());

        /*if internet is connected will fetch meal from remote data source*/
        if (Uitlity.isInternetConnected(getApplicationContext())) {
            /*request meal from remote data source */
            remoteDataPresenter = new RemoteDataPresenter(RemoteDataSource.getInstance(), this);
            remoteDataPresenter.getRemoteMealDetailsByName(mealName);
            /*change add to favourite button based on is meal is added to favourite or not */

            btnAddFav.setOnClickListener((View) -> {
                if (mealtoInsert.getIsAddedToFavourite() == null || mealtoInsert.getIsAddedToFavourite() == false) {
                    localDataPresenter.insertMealToFavorite(mealtoInsert);
                    Toast.makeText(MealDetailsActivity.this, "meal added to favourite", Toast.LENGTH_SHORT).show();
                    mealtoInsert.setAddedToFavTrue();
                } else if (mealtoInsert.getIsAddedToFavourite() == true) {

                    localDataPresenter.deleteMealFromFavourite(mealtoInsert);
                    mealtoInsert.setIsAddedToFavFalse();
                }
            });
        } else {
            String comeFrom = intent.getStringExtra(fromSrc) ;
            if("fav".equals(comeFrom)) {
                /*if internet is not connected will fetch meal from DATA BASE */
                Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
                LiveData<MealDetails> liveData = localDataPresenter.getFavouriteMealByName(mealName);
                observer = new Observer<MealDetails>() {
                    @Override
                    public void onChanged(MealDetails mealDetails) {
                        /*put meal details to List to use same View meal details funtion taht require an array list of meal details*/
                        List<MealDetails> localMeal = new ArrayList<>();
                        if (mealDetails != null) {
                            localMeal.add(mealDetails);
                            viewMealDetails(localMeal);
                        }

                    }
                };
                liveData.observe(this, observer);
            }
            else if ("plan".equals(comeFrom)){
                /*if internet is not connected will fetch meal from DATA BASE */
                LiveData<MealDetails> liveDataPlan = weekPlanPresenter.getMealFromPlanByName(mealName);
                observerPlan = new Observer<MealDetails>() {
                    @Override
                    public void onChanged(MealDetails mealPlan) {
                        /*put meal details to List to use same View meal details funtion taht require an array list of meal details*/
                        List<MealDetails> localMeal = new ArrayList<>();
                        if (mealPlan != null) {
                            localMeal.add(mealPlan);
                            viewMealDetails(localMeal);
                        }

                    }
                };
                liveDataPlan.observe(this, observerPlan);
            }
        }

        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showPlanDaysDialog();
                showCalenderDialog();
            }
        });
    }

    @Override
    public void viewMealDetails(List<MealDetails> mealDetails) {

        mealtoInsert = mealDetails.get(0);
        // changeFavButtonIMG(mealDetails.get(0));
        /*view main detila image */
        Glide.with(this).load(mealDetails.get(0).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(imgDetMeal);

        /*view meal name */
        txtMealName.setText(mealDetails.get(0).getStrMeal().toString());
        /*view insdtructions*/
        txtMelInstruction.setText(mealDetails.get(0).getStrInstructions());

        /*view youtube video*/
        String videoURL = mealDetails.get(0).getStrYoutube();
        viewInstructionVideo(videoURL);

        /*view ingredient images and description*/
        ingredientAdapter = new IngredientAdapter(this, recyclerView, mealDetails);
        recyclerView.setAdapter(ingredientAdapter);
    }

    /*converting YOUTUBE URL to an embed url to view just video not all the page of the url */
    public void viewInstructionVideo(final String videoURL) {
        webViewvideo.getSettings().setJavaScriptEnabled(true);
        if (videoURL != null && videoURL.contains("=")) {

            String videoId = videoURL.substring(videoURL.lastIndexOf('=') + 1);
            String embedUrl = "https://www.youtube.com/embed/" + videoId;

            webViewvideo.getSettings().setJavaScriptEnabled(true);
            webViewvideo.getSettings().setDomStorageEnabled(true);
            webViewvideo.setWebViewClient(new WebViewClient());
            // Load the embed URL in WebView
            webViewvideo.loadUrl(embedUrl);

        }
    }



    private void changeFavButtonIMG(MealDetails mealDetails) {

        btnAddFav.setImageDrawable(getResources().getDrawable(R.drawable.delete_fav));

    }


    private void showCalenderDialog() {
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = (String.format("%02d", day) + "-" + String.format("%02d", (month + 1)) + "-" + year);
                MealPlan mealPlan = new MealPlan(mealtoInsert);
                mealPlan.setPlanDayDate(date);
                weekPlanPresenter.insertMealToWeekPlan(mealPlan);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}


