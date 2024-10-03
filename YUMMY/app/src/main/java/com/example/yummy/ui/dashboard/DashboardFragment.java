package com.example.yummy.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Model.Category;
import com.example.yummy.Model.Counrty;
import com.example.yummy.Model.Meal;
import com.example.yummy.Model.Ingredient;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.RemoteMealPresenter.RemoteDataPresenter;
import com.example.yummy.databinding.FragmentSearchBinding;

import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements ISearchView , OnClickListner{
    public static final  String counrtyKey = "CountytyName" ;
    public static final String categoryKey = "CatrgotyName";
    public static  final  String ingredientKey = "IngredientName" ;

    private FragmentSearchBinding binding;
    private SearchView searchView ;
   private RecyclerView recyclerViewSrchCateg ;
   private RemoteDataPresenter remoteDataPresenter;
   private SearchAdapter searchAdapter ;
   private RecyclerView recyclerViewSrchCounrty ;
   private SearchCounrtyAdapter searchCounrtyAdapter ;
   private OnClickListner onClickListner ;
   private RecyclerView recycViewMealOnClick ;
   private LinearLayout  LinearSearchlayout ;
   private FilterMealAdapter  filterMealAdapter ;
   private IngredientAdapter ingredientAdapter ;
   private RecyclerView srchcIngrdRcycView ;
   private SearchView searchBar ;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        view.findViewById(R.id.srchBar) ;
        recyclerViewSrchCateg  = view.findViewById(R.id.srchcCtegRcycView);
        recyclerViewSrchCounrty = view.findViewById(R.id.srchcCountyRcycView) ;
        srchcIngrdRcycView = view.findViewById(R.id.srchcIngrdRcycView);
        LinearSearchlayout= view.findViewById(R.id.LinearSearchlayout) ;
        searchBar = view.findViewById(R.id.srchBar);


        remoteDataPresenter = new RemoteDataPresenter(RemoteDataSource.getInstance(), this );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext(),RecyclerView.HORIZONTAL , false);
        recyclerViewSrchCateg.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(getContext().getApplicationContext(),  recyclerViewSrchCateg,  new ArrayList<Category>() , this) ;
        recyclerViewSrchCateg.setAdapter(searchAdapter);

        LinearLayoutManager linearLayoutManagerCountry = new LinearLayoutManager(getContext().getApplicationContext() ,RecyclerView.HORIZONTAL , false);
        linearLayoutManagerCountry.setOrientation(RecyclerView.HORIZONTAL);

        recyclerViewSrchCounrty.setLayoutManager(linearLayoutManagerCountry);
        searchCounrtyAdapter = new SearchCounrtyAdapter(getContext().getApplicationContext() , recyclerViewSrchCounrty , new ArrayList<Counrty>() ,this) ;
        recyclerViewSrchCounrty.setAdapter(searchCounrtyAdapter);


        LinearLayoutManager linearLayoutManagerIngredient = new LinearLayoutManager(getContext()) ;
        linearLayoutManagerIngredient.setOrientation(LinearLayoutManager.HORIZONTAL);

        srchcIngrdRcycView.setLayoutManager(linearLayoutManagerIngredient);
        ingredientAdapter= new IngredientAdapter(getContext(), srchcIngrdRcycView, new ArrayList<Ingredient>() , this.onClickListner) ;
        srchcIngrdRcycView.setAdapter(ingredientAdapter);



        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        verticalLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewSrchCateg.setLayoutManager(verticalLayoutManager);

        filterMealAdapter = new FilterMealAdapter(getContext(), recycViewMealOnClick, new ArrayList<Meal>());


    }

    @Override
    public void onResume(){

        super.onResume();
        remoteDataPresenter.getRemoteCatigoreis();
        remoteDataPresenter.getRemoteCountries();
        remoteDataPresenter.getRemoteIngredient();
        searchBar.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                remoteDataPresenter.getRemoteSearchMealByName(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                HideWithoutSearchBar();
                recyclerViewSrchCateg.setAdapter(filterMealAdapter);
                remoteDataPresenter.getRemoteSearchMealByName(s);
                return true;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        searchBar.setOnQueryTextListener(null);
        filterMealAdapter.setFilterMealList(new ArrayList<>());
        binding = null;
    }

    @Override
    public void viewSearchCategory(List<Category> categories) {
        searchAdapter.setSearchCategoriesList(categories);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewSearchCounrty(List<Counrty> countries) {
        searchCounrtyAdapter.setCounrtiesList(countries);
        searchCounrtyAdapter.notifyDataSetChanged();
    }
    @Override
    public void viewIngredient(List<Ingredient> ingredients) {
        // pass data to adapter to show it on
        ingredientAdapter.setIngredientList(ingredients);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewSearchMealBYName(List<Meal> searchedMeals) {
        // passing to adpter
        if(!(searchedMeals == null || searchedMeals.isEmpty())) {
            filterMealAdapter.setFilterMealList(searchedMeals);
            filterMealAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCountryClick(Counrty counrty) {
        Intent toMealsListIntent = new Intent(getContext() , MealsListActivity.class) ;
        toMealsListIntent.putExtra(counrtyKey, counrty.getStrArea()) ;
        DashboardFragment.this.startActivity(toMealsListIntent);

    }

    @Override
    public void onCatgeoryClick(String catgeoryName) {
        Intent toMealsListIntent = new Intent(getContext() , MealsListActivity.class) ;
        toMealsListIntent.putExtra(categoryKey, catgeoryName) ;
        DashboardFragment.this.startActivity(toMealsListIntent);
    }

    @Override
    public void onIngerdientClick(String ingredientName) {
        Intent toMealsListIntent = new Intent(getContext() , MealsListActivity.class) ;
        toMealsListIntent.putExtra(ingredientKey, ingredientName) ;
        DashboardFragment.this.startActivity(toMealsListIntent);

    }





    private void HideWithoutSearchBar(){
        binding.srchcCountyRcycView.setVisibility(View.INVISIBLE);
        binding.srchcCtegRcycView.setVisibility(View.VISIBLE);
        binding.textView.setVisibility(View.INVISIBLE);
        binding.textView2.setVisibility(View.INVISIBLE);
        binding.textView3.setVisibility(View.INVISIBLE);
        binding.srchcIngrdRcycView.setVisibility(View.INVISIBLE);
//        binding.recycViewMealOnClick.setVisibility(View.INVISIBLE);
    }

}