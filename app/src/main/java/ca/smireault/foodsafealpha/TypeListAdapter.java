package ca.smireault.foodsafealpha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class TypeListAdapter extends RecyclerView.Adapter<TypeListAdapter.TypeViewHolder> {

    private final LayoutInflater inflater;
    private List<Product> products;
    private List<Type> types;
    private Context mContext;
    private final int NEW_PRODUCT_REQUEST_CODE = 1;

    TypeListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View productView = inflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new TypeViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder typeViewHolder, int i) {
        if (types != null) {

            final Type current = types.get(i);
            typeViewHolder.typeItemView.setText(current.getName());

            typeViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProductListActivity.class);
                    intent.putExtra("type_id", current.getId());
                    mContext.startActivity(intent);
                }
            });

        } else {
            typeViewHolder.typeItemView.setText("No type");
        }
    }

    void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    void setTypes(List<Type> types) {
        this.types = types;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (types != null) {
            return types.size();
        } else {
            return 0;
        }
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {
        private final TextView typeItemView;
        LinearLayout parentLayout;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);

            typeItemView = itemView.findViewById(R.id.textView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
