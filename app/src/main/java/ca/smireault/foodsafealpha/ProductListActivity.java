package ca.smireault.foodsafealpha;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private int type_id;
    private final int NEW_PRODUCT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.product_list_recycler);
        final ProductListAdapter adapter = new ProductListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        final TypeListAdapter typeAdapter = new TypeListAdapter(this);
//        recyclerView.setAdapter(typeAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        Intent intent = getIntent();
        type_id = intent.getIntExtra("type_id", 1);

        productViewModel.getAllProductsByType(type_id).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapter.setProducts(products);
            }
        });

//        productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
//            @Override
//            public void onChanged(@Nullable List<Product> products) {
//                adapter.setProducts(products);
//            }
//        });

//        productViewModel.getAllTypes().observe(this, new Observer<List<Type>>() {
//            @Override
//            public void onChanged(@Nullable List<Type> types) {
//                typeAdapter.setTypes(types);
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, NewProductActivity.class);
                intent.putExtra("type_id", type_id);
                startActivityForResult(intent, NEW_PRODUCT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK) {
            Product product = new Product(data.getStringExtra("product_name"), type_id);
            productViewModel.insert(product);
        } else {
            Toast.makeText(this, "Product not saved", Toast.LENGTH_LONG).show();
        }
    }
}
