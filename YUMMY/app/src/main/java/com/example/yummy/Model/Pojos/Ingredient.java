package com.example.yummy.Model.Pojos;

public class Ingredient {
    public String idIngredient;
    public String strIngredient;
    public String strDescription;
    public Object strType;

    public Object getStrType() {
        return strType;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public void setStrType(Object strType) {
        this.strType = strType;
    }

    public Ingredient(String idIngredient, String strIngredient, String strDescription, Object strType) {
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
        this.strDescription = strDescription;
        this.strType = strType;
    }

    public String getIdIngredient() {
        return idIngredient;
    }
}
