package ca.smireault.foodsafealpha;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<Type>> allTypes;

    public ProductViewModel (Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
        allTypes = repository.getAllTypes();
    }

    LiveData<List<Product>> getAllProducts() { return allProducts; }
    LiveData<List<Type>> getAllTypes() { return allTypes; }

    public void insert(Product product) {
        repository.insert(product);
    }
}