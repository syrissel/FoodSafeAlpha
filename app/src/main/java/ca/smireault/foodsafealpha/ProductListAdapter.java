package ca.smireault.foodsafealpha;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private final LayoutInflater mInflator;
    private List<Product> mProducts;
    private Context mContext;
    private final int NEW_PRODUCT_REQUEST_CODE = 1;

    ProductListAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View productView = mInflator.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        if (mProducts != null) {
            final Product current = mProducts.get(i);
            productViewHolder.productItemView.setText(current.getName());

            productViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, RecipesActivity.class);
                    intent.putExtra("product_name", current.getName());
                    Log.d("Steph", "Clicked");
                    mContext.startActivity(intent);
                }
            });


        } else {
            productViewHolder.productItemView.setText("No product");
        }
    }

    @Override
    public int getItemCount() {
        if (mProducts != null) {
            return mProducts.size();
        } else {
            return 0;
        }
    }

    void setProducts(List<Product> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productItemView;
        LinearLayout parentLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productItemView = itemView.findViewById(R.id.textView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
