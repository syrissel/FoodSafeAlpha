package ca.smireault.foodsafealpha;

public class RecipeItem {

    private String mImageUrl;
    private String mRecipeName;
    private String mIngredients;
    private String mUrl;

    public RecipeItem(String imageUrl, String recipeName, String ingredients, String url) {
        mImageUrl = imageUrl;
        mRecipeName = recipeName;
        mIngredients = ingredients;
        mUrl = url;
    }

    public String getmUrl() { return mUrl; }

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
