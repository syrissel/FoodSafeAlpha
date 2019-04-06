package ca.smireault.foodsafealpha;

public class RecipeItem {

    private String mImageUrl;
    private String mRecipeName;
    private String mIngredients;

    public RecipeItem(String imageUrl, String recipeName, String ingredients) {
        mImageUrl = imageUrl;
        mRecipeName = recipeName;
        mIngredients = ingredients;
    }


    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmRecipeName() {
        return mRecipeName;
    }

    public String getmIngredients() {
        return mIngredients;
    }
}
