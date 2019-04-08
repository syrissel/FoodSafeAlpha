package ca.smireault.foodsafealpha;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAddProduct;
    private ProductViewModel productViewModel;
    public static final int NEW_PRODUCT_REQUEST_CODE = 1;
    private FloatingActionButton fab;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        final TypeListAdapter adapter = new TypeListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapter.setProducts(products);
            }
        });

        productViewModel.getAllTypes().observe(this, new Observer<List<Type>>() {
            @Override
            public void onChanged(@Nullable List<Type> types) {
                adapter.setTypes(types);
            }
        });

        fab = findViewById(R.id.fab);
        fab.bringToFront();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                startActivityForResult(intent, NEW_PRODUCT_REQUEST_CODE);
            }
        });

        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnAddProduct.bringToFront();

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                startActivityForResult(intent, NEW_PRODUCT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK) {
            //Product product = new Product(data.getStringExtra(NewProductActivity.EXTRA_REPLY), 1);
            //productViewModel.insert(product);
        } else {
            Toast.makeText(this, "Product not saved.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        int sortOrder = Integer.valueOf(mSharedPreferences.getString("sorting_options", "0"));
        final TypeListAdapter adapter = new TypeListAdapter(this);

        if (sortOrder == 0) {
            productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
                @Override
                public void onChanged(@Nullable List<Product> products) {
                    adapter.setProducts(products);
                }
            });

            productViewModel.getAllTypes().observe(this, new Observer<List<Type>>() {
                @Override
                public void onChanged(@Nullable List<Type> types) {
                    adapter.setTypes(types);
                }
            });
        } else {

            productViewModel.getAllProductsDesc().observe(this, new Observer<List<Product>>() {
                @Override
                public void onChanged(@Nullable List<Product> products) {
                    adapter.setProducts(products);
                }
            });

            productViewModel.getAllTypesDesc().observe(this, new Observer<List<Type>>() {
                @Override
                public void onChanged(@Nullable List<Type> types) {
                    adapter.setTypes(types);
                }
            });
        }
    }
}
