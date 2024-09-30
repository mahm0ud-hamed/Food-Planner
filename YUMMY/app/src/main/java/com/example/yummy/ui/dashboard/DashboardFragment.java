package com.example.yummy.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Model.Category;
import com.example.yummy.Model.Counrty;
import com.example.yummy.Model.CountryMeal;
import com.example.yummy.Model.Ingredient;
import com.example.yummy.Network.NetWorkCallBack;
import com.example.yummy.Network.RemoteDataSource;
import com.example.yummy.R;
import com.example.yummy.RandoMealPresenter.RemoteDataPresenter;
import com.example.yummy.Repsitory.Reposiory;
import com.example.yummy.databinding.FragmentSearchBinding;
import com.google.android.material.search.SearchBar;
import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements ISearchView , OnClickListner{

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
        recycViewMealOnClick = view.findViewById(R.id.recycViewMealOnClick) ;
        srchcIngrdRcycView = view.findViewById(R.id.srchcIngrdRcycView);
        LinearSearchlayout= view.findViewById(R.id.LinearSearchlayout) ;
        searchBar = view.findViewById(R.id.srchBar);
        remoteDataPresenter = new RemoteDataPresenter(RemoteDataSource.getInstance(), this );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext(),RecyclerView.HORIZONTAL , false);
      //  linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewSrchCateg.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(getContext().getApplicationContext(),  recyclerViewSrchCateg,  new ArrayList<Category>() , this) ;
        recyclerViewSrchCateg.setAdapter(searchAdapter);

        LinearLayoutManager linearLayoutManagerCountry = new LinearLayoutManager(getContext().getApplicationContext() ,RecyclerView.HORIZONTAL , false);
       // linearLayoutManagerCountry.setOrientation(RecyclerView.HORIZONTAL);

        recyclerViewSrchCounrty.setLayoutManager(linearLayoutManagerCountry);
        searchCounrtyAdapter = new SearchCounrtyAdapter(getContext().getApplicationContext() , recyclerViewSrchCounrty , new ArrayList<Counrty>() ,this) ;
        recyclerViewSrchCounrty.setAdapter(searchCounrtyAdapter);


        LinearLayoutManager linearLayoutManagerIngredient = new LinearLayoutManager(getContext()) ;
        linearLayoutManagerIngredient.setOrientation(LinearLayoutManager.HORIZONTAL);

        srchcIngrdRcycView.setLayoutManager(linearLayoutManagerIngredient);
         ingredientAdapter= new IngredientAdapter(getContext(), srchcIngrdRcycView, new ArrayList<Ingredient>() , this) ;
        srchcIngrdRcycView.setAdapter(ingredientAdapter);

        remoteDataPresenter.getRemoteCatigoreis();
        remoteDataPresenter.getRemoteCountries();
        remoteDataPresenter.getRemoteIngredient();

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        verticalLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSrchCateg.setLayoutManager(verticalLayoutManager);

        filterMealAdapter = new FilterMealAdapter(getContext(), recycViewMealOnClick, new ArrayList<CountryMeal>());
        recyclerViewSrchCateg.setAdapter(filterMealAdapter);

        searchBar.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                remoteDataPresenter.getRemoteSearchMealByName(s);
                return true;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
    public void viewCountryMealsByFilter(List<CountryMeal> filterCountryMeals) {
        // pas to adapter when caeated
        filterMealAdapter.setFilterMealList(filterCountryMeals);
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewCategoryMealsByFilter(List<CountryMeal> filterCategoryMeals) {
        filterMealAdapter.setFilterMealList(filterCategoryMeals);
        filterMealAdapter.notifyDataSetChanged();
    }



    @Override
    public void viewIngredientMealsByFilter(List<CountryMeal> filterIngredientMeals) {
        // paass it to adapter to show meals
        filterMealAdapter.setFilterMealList(filterIngredientMeals);
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewSearchMealBYName(List<CountryMeal> searchedMeals) {
        // passing to adpter
        if(!(searchedMeals == null || searchedMeals.isEmpty())) {
            filterMealAdapter.setFilterMealList(searchedMeals);
            filterMealAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCountryClick(Counrty counrty) {
        HideSearchFragmentEelement() ;
        binding.recycViewMealOnClick.setVisibility(View.VISIBLE);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        verticalLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycViewMealOnClick.setLayoutManager(verticalLayoutManager);

        // Ensure that you have the correct data for the new adapter
        filterMealAdapter = new FilterMealAdapter(getContext(), recycViewMealOnClick, new ArrayList<CountryMeal>());
        recycViewMealOnClick.setAdapter(filterMealAdapter);

        remoteDataPresenter.getRemoteFilterCounrtyMeal(counrty.getStrArea());
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCatgeoryClick(String catgeorName) {

        HideSearchFragmentEelement() ;
        //binding.recycViewMealOnClick.setVisibility(View.VISIBLE);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        verticalLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSrchCateg.setLayoutManager(verticalLayoutManager);

        filterMealAdapter = new FilterMealAdapter(getContext(), recycViewMealOnClick, new ArrayList<CountryMeal>());
        recyclerViewSrchCateg.setAdapter(filterMealAdapter);

        remoteDataPresenter.getRemoteFilterCategoryMeal(catgeorName);
        filterMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void onIngerdientClick(String ingredientName) {
        HideSearchFragmentEelement() ;
        //binding.recycViewMealOnClick.setVisibility(View.VISIBLE);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        verticalLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSrchCateg.setLayoutManager(verticalLayoutManager);

        filterMealAdapter = new FilterMealAdapter(getContext(), recycViewMealOnClick, new ArrayList<CountryMeal>());
        recyclerViewSrchCateg.setAdapter(filterMealAdapter);

        remoteDataPresenter.getRemoteFilterIngredientMeal(ingredientName);
        filterMealAdapter.notifyDataSetChanged();
    }

//    @Override
//    public void onSearchBarWriteListner(String mealName) {
//        HideSearchFragmentEelement();
//        binding.srchBar.setVisibility(View.VISIBLE);
//        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
//        verticalLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerViewSrchCateg.setLayoutManager(verticalLayoutManager);
//
//        filterMealAdapter = new FilterMealAdapter(getContext(), recycViewMealOnClick, new ArrayList<CountryMeal>());
//        recyclerViewSrchCateg.setAdapter(filterMealAdapter);
//        remoteDataPresenter.getRemoteSearchMealByName(mealName);
//        filterMealAdapter.notifyDataSetChanged();
//
//    }


    private void HideSearchFragmentEelement(){
        binding.srchBar.setVisibility(View.INVISIBLE);
        binding.srchcCountyRcycView.setVisibility(View.INVISIBLE);
        //binding.srchcCtegRcycView.setVisibility(View.INVISIBLE);
        binding.textView.setVisibility(View.INVISIBLE);
        binding.textView2.setVisibility(View.INVISIBLE);
        binding.textView3.setVisibility(View.INVISIBLE);
        binding.srchcIngrdRcycView.setVisibility(View.INVISIBLE);

    }

    private void HideWithoutSearchBar(){
        binding.srchcCountyRcycView.setVisibility(View.INVISIBLE);
        binding.srchcCtegRcycView.setVisibility(View.INVISIBLE);
        binding.textView.setVisibility(View.INVISIBLE);
        binding.textView2.setVisibility(View.INVISIBLE);
        binding.textView3.setVisibility(View.INVISIBLE);
        binding.srchcIngrdRcycView.setVisibility(View.INVISIBLE);
        binding.recycViewMealOnClick.setVisibility(View.VISIBLE);
    }

}