package com.example.yummy.ui.weekPlan;

import com.example.yummy.Model.Pojos.MealDetails;
import com.example.yummy.Model.Pojos.MealPlan;

public interface planCLickListner {
    public void removeMealFromPlan(MealPlan mealPlan) ;
    public void showPlanMealDetails(String MealName) ;
}
