package com.example.yummy.Model.Pojos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "favourite_table")
public class MealDetails {

    public MealDetails(){}
    @PrimaryKey
    @NonNull
    public String idMeal;

    public String strMeal;
    public String strDrinkAlternate;
    public String strCategory;
    @ColumnInfo(name = "Country")
    public String strArea;
    public String strInstructions;
    public String strMealThumb;
    public String strTags;
    public String strYoutube;
    public String strIngredient1;
    public String strIngredient2;
    public String strIngredient3;
    public String strIngredient4;
    public String strIngredient5;
    public String strIngredient6;
    public String strIngredient7;
    public String strIngredient8;
    public String strIngredient9;
    public String strIngredient10;
    public String strIngredient11;
    public String strIngredient12;
    public String strIngredient13;
    public String strIngredient14;
    public String strIngredient15;

    public String strIngredient16;
    public String strIngredient17;
    public String strIngredient18;
    public String strIngredient19;
    public String strIngredient20;

    public String strMeasure1;
    public String strMeasure2;
    public String strMeasure3;
    public String strMeasure4;
    public String strMeasure5;
    public String strMeasure6;
    public String strMeasure7;
    public String strMeasure8;
    public String strMeasure9;
    public String strMeasure10;
    public String strMeasure11;
    public String strMeasure12;
    public String strMeasure13;
    public String strMeasure14;
    public String strMeasure15;

    public String strMeasure16;
    public String strMeasure17;
    public String strMeasure18;
    public String strMeasure19;
    public String strMeasure20;
    public String strSource;
    public String strImageSource;
    public String strCreativeCommonsConfirmed;
    public String dateModified;
    public Boolean isAddedToFavourite ;

    public String getIdMeal() {
        return idMeal;
    }

    public Boolean getIsAddedToFavourite() {
        return isAddedToFavourite;
    }
    public void setAddedToFavTrue(){
        isAddedToFavourite =true ;
    }
    public  void setIsAddedToFavFalse(){
        isAddedToFavourite =false ;
    }


    public MealDetails(String idMeal, String strMeal, String strDrinkAlternate, String strCategory, String strArea,
                       String strInstructions, String strMealThumb, String strTags, String strYoutube, String strIngredient1,
                       String strIngredient2, String strIngredient3, String strIngredient4, String strIngredient5, String strIngredient6,
                       String strIngredient7, String strIngredient8, String strIngredient9, String strIngredient10, String strIngredient11,
                       String strIngredient12, String strIngredient13, String strIngredient14, String strIngredient15, String strIngredient16,
                       String strIngredient17, String strIngredient18, String strIngredient19, String strIngredient20, String strMeasure1, String strMeasure2,
                       String strMeasure3, String strMeasure4, String strMeasure5, String strMeasure6, String strMeasure7, String strMeasure8, String strMeasure9,
                       String strMeasure10, String strMeasure11, String strMeasure12, String strMeasure13, String strMeasure14, String strMeasure15, String strMeasure16,
                       String strMeasure17, String strMeasure18, String strMeasure19, String strMeasure20, String strSource,
                       String strImageSource, String strCreativeCommonsConfirmed, String dateModified) {

        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strDrinkAlternate = strDrinkAlternate;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strTags = strTags;
        this.strYoutube = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.strMeasure1 = strMeasure1;
        this.strMeasure2 = strMeasure2;
        this.strMeasure3 = strMeasure3;
        this.strMeasure4 = strMeasure4;
        this.strMeasure5 = strMeasure5;
        this.strMeasure6 = strMeasure6;
        this.strMeasure7 = strMeasure7;
        this.strMeasure8 = strMeasure8;
        this.strMeasure9 = strMeasure9;
        this.strMeasure10 = strMeasure10;
        this.strMeasure11 = strMeasure11;
        this.strMeasure12 = strMeasure12;
        this.strMeasure13 = strMeasure13;
        this.strMeasure14 = strMeasure14;
        this.strMeasure15 = strMeasure15;
        this.strMeasure16 = strMeasure16;
        this.strMeasure17 = strMeasure17;
        this.strMeasure18 = strMeasure18;
        this.strMeasure19 = strMeasure19;
        this.strMeasure20 = strMeasure20;
        this.strSource = strSource;
        this.strImageSource = strImageSource;
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
        this.dateModified = dateModified;
    }

    /*copy Constructor*/
    public MealDetails (MealDetails mealDetails){
        this.idMeal = mealDetails.idMeal;
        this.strMeal = mealDetails.strMeal;
        this.strDrinkAlternate = mealDetails.strDrinkAlternate;
        this.strCategory = mealDetails.strCategory;
        this.strArea = mealDetails.strArea;
        this.strInstructions =mealDetails. strInstructions;
        this.strMealThumb = mealDetails.strMealThumb;
        this.strTags = mealDetails.strTags;
        this.strYoutube =mealDetails.strYoutube;
        this.strIngredient1 =mealDetails.strIngredient1;
        this.strIngredient2 = mealDetails.strIngredient2;
        this.strIngredient3 = mealDetails.strIngredient3;
        this.strIngredient4 = mealDetails.strIngredient4;
        this.strIngredient5 = mealDetails.strIngredient5;
        this.strIngredient6 = mealDetails.strIngredient6;
        this.strIngredient7 = mealDetails.strIngredient7;
        this.strIngredient8 = mealDetails.strIngredient8;
        this.strIngredient9 = mealDetails.strIngredient9;
        this.strIngredient10 = mealDetails.strIngredient10;
        this.strIngredient11 = mealDetails.strIngredient11;
        this.strIngredient12 = mealDetails.strIngredient12;
        this.strIngredient13 = mealDetails.strIngredient13;
        this.strIngredient14 = mealDetails.strIngredient14;
        this.strIngredient15 = mealDetails.strIngredient15;
        this.strIngredient16 = mealDetails.strIngredient16;
        this.strIngredient17 = mealDetails.strIngredient17;
        this.strIngredient18 = mealDetails.strIngredient18;
        this.strIngredient19 = mealDetails.strIngredient19;
        this.strIngredient20 = mealDetails.strIngredient20;
        this.strMeasure1 = mealDetails.strMeasure1;
        this.strMeasure2 = mealDetails.strMeasure2;
        this.strMeasure3 = mealDetails.strMeasure3;
        this.strMeasure4 = mealDetails.strMeasure4;
        this.strMeasure5 = mealDetails.strMeasure5;
        this.strMeasure6 = mealDetails.strMeasure6;
        this.strMeasure7 = mealDetails.strMeasure7;
        this.strMeasure8 = mealDetails.strMeasure8;
        this.strMeasure9 = mealDetails.strMeasure9;
        this.strMeasure10 = mealDetails.strMeasure10;
        this.strMeasure11 = mealDetails.strMeasure11;
        this.strMeasure12 = mealDetails.strMeasure12;
        this.strMeasure13 = mealDetails.strMeasure13;
        this.strMeasure14 = mealDetails.strMeasure14;
        this.strMeasure15 = mealDetails.strMeasure15;
        this.strMeasure16 = mealDetails.strMeasure16;
        this.strMeasure17 = mealDetails.strMeasure17;
        this.strMeasure18 = mealDetails.strMeasure18;
        this.strMeasure19 = mealDetails.strMeasure19;
        this.strMeasure20 = mealDetails.strMeasure20;
        this.strSource = mealDetails.strSource;
        this.strImageSource = mealDetails.strImageSource;
        this.strCreativeCommonsConfirmed = mealDetails.strCreativeCommonsConfirmed;
        this.dateModified = mealDetails.dateModified;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public Object getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public Object getStrIngredient16() {
        return strIngredient16;
    }

    public Object getStrIngredient17() {
        return strIngredient17;
    }

    public Object getStrIngredient18() {
        return strIngredient18;
    }

    public Object getStrIngredient19() {
        return strIngredient19;
    }

    public Object getStrIngredient20() {
        return strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public Object getStrMeasure16() {
        return strMeasure16;
    }

    public Object getStrMeasure17() {
        return strMeasure17;
    }

    public Object getStrMeasure18() {
        return strMeasure18;
    }

    public Object getStrMeasure19() {
        return strMeasure19;
    }

    public Object getStrMeasure20() {
        return strMeasure20;
    }

    public Object getStrSource() {
        return strSource;
    }

    public Object getStrImageSource() {
        return strImageSource;
    }

    public Object getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public Object getDateModified() {
        return dateModified;
    }



        /*helper functions that will make all ingredient name in array list to make it east to parse in adapater */
    public List<String> getAllIngredientNames(){
        List<String> ingreidentName = new ArrayList<>() ;
        for (int i =1 ; i <= 20 ; i++){
            try {
                Field field = this.getClass().getDeclaredField("strIngredient"+i);
                String value = (String) field.get(this) ;
                if(value != null && !value.isEmpty()){
                    ingreidentName.add(value) ;
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return ingreidentName ;
    }

    /*helper functions that will make all ingredient Amount in array list to make it east to parse in adapater */

    public List<String> getAllIngredientAmount(){
        List<String> ingreidentAmount = new ArrayList<>() ;
        for (int i =1 ; i <= 20 ; i++){
            try {
                Field field = this.getClass().getDeclaredField("strMeasure"+i);
                String value = (String) field.get(this) ;
                if(value != null && !value.isEmpty()){
                    ingreidentAmount.add(value) ;
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return ingreidentAmount ;
    }
}
