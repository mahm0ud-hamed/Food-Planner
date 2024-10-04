package com.example.yummy.ui.Details;

import static com.example.yummy.ui.home.HomeFragment.MealKey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.MealPresenter.LocalDataPresenter;
import com.example.yummy.Model.DataBase.DataBase;
import com.example.yummy.Model.DataBase.MealDao;
import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.MealPresenter.RemoteDataPresenter;
import com.example.yummy.ui.Srearch.IngredientAdapter;

import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements IMealDetail {
    RemoteDataPresenter remoteDataPresenter;
    ImageView imgDetMeal;
    TextView txtMealName;
    TextView txtMelInstruction;
    WebView webViewvideo;
    RecyclerView recyclerView ;
    IngredientAdapter ingredientAdapter ;
    ImageButton btnAddFav ;
    LocalDataPresenter localDataPresenter ;
    MealDetails mealtoInsert ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        Intent intent = getIntent();
        String mealName = intent.getStringExtra(MealKey).toString();
        imgDetMeal = findViewById(R.id.imgDetMeal);
        txtMealName = findViewById(R.id.txtDetMealName);
        txtMelInstruction = findViewById(R.id.txtInstruction);
        recyclerView = findViewById(R.id.recycViewDetIngred) ;
        webViewvideo = findViewById(R.id.webViewVideo);
        btnAddFav = findViewById(R.id.btnAddtFav) ;

        /*creating layout manager*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); ;
        recyclerView.setLayoutManager(layoutManager);

        /*request meal from remote data source */
        remoteDataPresenter = new RemoteDataPresenter(RemoteDataSource.getInstance(), this);
        remoteDataPresenter.getRemoteMealDetailsByName(mealName);

        /*creaitng instance from data base */
        localDataPresenter= new LocalDataPresenter(DataBase.getInstance(this).getMealDAO()) ;

        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localDataPresenter.insertMealToFavorite(mealtoInsert);

            }
        });
    }

    @Override
    public void viewMealDetails(List<MealDetails> mealDetails) {

        mealtoInsert = mealDetails.get(0);
        /*view main detila image */
        Glide.with(this).load(mealDetails.get(0).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(imgDetMeal);

        /*view meal name */
        txtMealName.setText(mealDetails.get(0).strMeal.toString());
        /*view insdtructions*/
        txtMelInstruction.setText(mealDetails.get(0).getStrInstructions());

        /*view youtube video*/
        String videoURL = mealDetails.get(0).getStrYoutube() ;
        viewInstructionVideo(videoURL);
        List<String> names;
        names = mealDetails.get(0).getAllIngredientNames();
        System.out.println("igredient name is "+names.get(1).toString());

        /*view ingredient images and description*/
        /*creeating adapter to show ingredient photos*/
        ingredientAdapter = new IngredientAdapter(this , recyclerView , mealDetails);
        recyclerView.setAdapter(ingredientAdapter);


    }

    public void viewInstructionVideo( final String videoURL) {
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
}


