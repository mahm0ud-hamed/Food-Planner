package com.example.yummy.ui.Details;

import static com.example.yummy.ui.home.HomeFragment.MealKey;

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

import com.bumptech.glide.Glide;
import com.example.yummy.MealPresenter.LocalDataPresenter;
import com.example.yummy.Model.DataBase.DataBase;
import com.example.yummy.Model.DataBase.WeekPlanDao;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Network.RemoteDataSource;
import com.example.yummy.Model.Pojos.MealPlan;
import com.example.yummy.R;
import com.example.yummy.MealPresenter.RemoteDataPresenter;
import com.example.yummy.ui.Srearch.IngredientAdapter;

import java.util.ArrayList;
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
    private LocalDataPresenter weekPlanPresenter ;
    private MealDetails mealtoInsert;
    private Observer<MealDetails> observer;
    public static final String saturDay="saturday";
    public static final String sunDay="sunday";
    public static final String monDay="monday";
    public static final String tuesDay="tuesday";
    public static final String wednsDay=" wednesday";
    public static final String thursDay="thursday";
    public static final String friDay="friday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        /*Receving data from intent */
        Intent intent = getIntent();
        String mealName = intent.getStringExtra(MealKey).toString();

        /*refrence on all UI objects */
        imgDetMeal = findViewById(R.id.imgDetMeal);
        txtMealName = findViewById(R.id.txtDetMealName);
        txtMelInstruction = findViewById(R.id.txtInstruction);
        recyclerView = findViewById(R.id.recycViewDetIngred);
        webViewvideo = findViewById(R.id.webViewVideo);
        btnAddFav = findViewById(R.id.btnAddtFav);
        btnAddPlan = findViewById(R.id.btnAddToPlan) ;

        /*creating layout manager to show all ingreident over an recycler view */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        /*creaitng instance from data base */
        localDataPresenter = new LocalDataPresenter(DataBase.getInstance(this).getMealDAO());
        weekPlanPresenter = new LocalDataPresenter(DataBase.getInstance(this).getWeekPlanDao()) ;

        /*if internet is connected will fetch meal from remote data source*/
        if (isInternetConnected()) {
            Toast.makeText(this, "InterNet is connected", Toast.LENGTH_SHORT).show();
            /*request meal from remote data source */
            remoteDataPresenter = new RemoteDataPresenter(RemoteDataSource.getInstance(), this);
            remoteDataPresenter.getRemoteMealDetailsByName(mealName);
            /*change add to favourite button based on is meal is added to favourite or not */

            btnAddFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mealtoInsert.getIsAddedToFavourite() == null || mealtoInsert.getIsAddedToFavourite() == false) {
                        localDataPresenter.insertMealToFavorite(mealtoInsert);
                        Toast.makeText(MealDetailsActivity.this, "meal added to favourite", Toast.LENGTH_SHORT).show();
                        mealtoInsert.setAddedToFavTrue();
                    } else if (mealtoInsert.getIsAddedToFavourite() == true) {

                        localDataPresenter.deleteMealFromFavourite(mealtoInsert);
                        mealtoInsert.setIsAddedToFavFalse();
                    }

                }
            });
        } else {
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

        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPlanDaysDialog();
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
            System.out.println(embedUrl);

            webViewvideo.getSettings().setJavaScriptEnabled(true);
            webViewvideo.getSettings().setDomStorageEnabled(true);
            webViewvideo.setWebViewClient(new WebViewClient());
            // Load the embed URL in WebView
            webViewvideo.loadUrl(embedUrl);

        }
    }

    /*chehcking if the network in connecting or not */
    private Boolean isInternetConnected() {
        Boolean internetStatus = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                internetStatus = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            } else {
                NetworkInfo activeNetWork = connectivityManager.getActiveNetworkInfo();
                internetStatus = activeNetWork != null && activeNetWork.isConnectedOrConnecting();
            }
        }
        return internetStatus;
    }

    private void changeFavButtonIMG(MealDetails mealDetails) {

        btnAddFav.setImageDrawable(getResources().getDrawable(R.drawable.delete_fav));

    }
    private void showPlanDaysDialog(){
        MealPlan mealPlan  = new MealPlan(mealtoInsert);
        LayoutInflater inflater = getLayoutInflater() ;
        View dialogView = inflater.inflate(R.layout.dialog_day_selection , null) ;
        AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
        builder.setTitle("Select Day") ;
        builder.setView(dialogView) ;

        Button mondayButton = dialogView.findViewById(R.id.button_monday);
        Button tuesdayButton = dialogView.findViewById(R.id.button_tuesday);
        Button wednesdayButton = dialogView.findViewById(R.id.button_wednesday);
        Button thursdayButton = dialogView.findViewById(R.id.button_thursday);
        Button fridayButton = dialogView.findViewById(R.id.button_friday);
        Button saturdayButton = dialogView.findViewById(R.id.button_saturday);
        Button sundayButton = dialogView.findViewById(R.id.button_sunday);
        saturdayButton.setOnClickListener(View->{
            mealPlan.setPlanDayName(saturDay);
            weekPlanPresenter.insertMealToWeekPlan(mealPlan);
            Toast.makeText(this, "meal add to "+saturDay, Toast.LENGTH_SHORT).show();

        });
        mondayButton.setOnClickListener(View->{
            mealPlan.setPlanDayName(monDay);
            weekPlanPresenter.insertMealToWeekPlan(mealPlan);
            Toast.makeText(this, "meal add to "+monDay, Toast.LENGTH_SHORT).show();
        });
        tuesdayButton.setOnClickListener(View->{
            mealPlan.setPlanDayName(tuesDay);
            weekPlanPresenter.insertMealToWeekPlan(mealPlan);
            Toast.makeText(this, "meal add to "+tuesDay, Toast.LENGTH_SHORT).show();
        });
        wednesdayButton.setOnClickListener(View -> {
            mealPlan.setPlanDayName(wednsDay);
            weekPlanPresenter.insertMealToWeekPlan(mealPlan);
            Toast.makeText(this, "meal add to "+wednsDay, Toast.LENGTH_SHORT).show();

        });
        thursdayButton.setOnClickListener(View->{
            mealPlan.setPlanDayName(thursDay);
            weekPlanPresenter.insertMealToWeekPlan(mealPlan);
            Toast.makeText(this, "meal add to "+thursDay, Toast.LENGTH_SHORT).show();

        });
        fridayButton.setOnClickListener(View->{
            mealPlan.setPlanDayName(friDay);
            weekPlanPresenter.insertMealToWeekPlan(mealPlan);
            Toast.makeText(this, "meal add to "+friDay, Toast.LENGTH_SHORT).show();

        });
        sundayButton.setOnClickListener(View ->{
            mealPlan.setPlanDayName(sunDay);
            weekPlanPresenter.insertMealToWeekPlan(mealPlan);
            Toast.makeText(this, "meal add to "+sunDay, Toast.LENGTH_SHORT).show();

        });
        builder.show();
    }
}


