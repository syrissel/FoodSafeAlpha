package ca.smireault.foodsafealpha;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context mContext;
    private ArrayList<RecipeItem> mRecipeList;

    public RecipeAdapter(Context context, ArrayList<RecipeItem> recipeList) {
        mContext = context;
        mRecipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        final RecipeItem currentItem = mRecipeList.get(i);

        String imageUrl = currentItem.getmImageUrl();
        String recipeName = currentItem.getmRecipeName();
        String ingredients = currentItem.getmIngredients();



        recipeViewHolder.mIngredients.setText(ingredients);
        recipeViewHolder.mRecipeName.setText(recipeName);

        try {
            Picasso.get().load(imageUrl).fit().centerInside().into(recipeViewHolder.mImageView);
        } catch(IllegalArgumentException e) {
            Picasso.get().load("http://clipart-library.com/images/pc7KKAXKi.jpg").fit().centerInside().into(recipeViewHolder.mImageView);
        }

        recipeViewHolder.mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.getmUrl()));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mRecipeName;
        public TextView mIngredients;
        public CardView mParentLayout;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mRecipeName = itemView.findViewById(R.id.tvRecipeName);
            mIngredients = itemView.findViewById(R.id.tvIngredients);
            mParentLayout = itemView.findViewById(R.id.card_view);
        }
    }
}
