package ca.smireault.foodsafealpha;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<Product>> allProductsDesc;
    private LiveData<List<Type>> allTypes;
    private LiveData<List<Type>> allTypesDesc;
    private LiveData<List<Product>> allProductsByType;

    public ProductViewModel (Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
        allProductsDesc = repository.getAllProductsDesc();
        allTypes = repository.getAllTypes();
        allTypesDesc = repository.getAllTypesDesc();
    }

    LiveData<List<Product>> getAllProducts() { return allProducts; }
    LiveData<List<Product>> getAllProductsDesc() { return allProductsDesc; }
    LiveData<List<Type>> getAllTypes() { return allTypes; }
    LiveData<List<Type>> getAllTypesDesc() {return allTypesDesc; }
    LiveData<List<Product>> getAllProductsByType(long type_id) {
        allProductsByType = repository.getAllProductsByType(type_id);
        return allProductsByType;
    }

    public void insert(Product product) {
        repository.insert(product);
    }
}
