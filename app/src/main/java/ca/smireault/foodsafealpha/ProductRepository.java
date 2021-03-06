package ca.smireault.foodsafealpha;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private TypeDao typeDao;
    private LiveData<List<Type>> allTypes;
    private LiveData<List<Type>> allTypesDesc;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<Product>> allProductsDesc;
    private LiveData<List<Product>> allProductsByType;

    ProductRepository(Application application) {
        ProductRoomDatabase db = ProductRoomDatabase.getInstance(application);
        productDao = db.productDao();
        typeDao = db.typeDao();
        allProducts = productDao.getAllProducts();
        allProductsDesc = productDao.getAllProductsDesc();
        allTypes = typeDao.getAllTypes();
        allTypesDesc = typeDao.getAllTypesDesc();
    }

    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
    LiveData<List<Product>> getAllProductsDesc() { return allProductsDesc; }
    LiveData<List<Type>> getAllTypes() { return allTypes; }
    LiveData<List<Type>> getAllTypesDesc() { return allTypesDesc; }

    LiveData<List<Product>> getAllProductsByType(long type_id) {
        allProductsByType = productDao.getAllProductsByType(type_id);
        return allProductsByType;
    }

    public void insert(Product product) {
        new insertAsyncTask(productDao).execute(product);
    }

    private static class insertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao asyncDao;

        insertAsyncTask(ProductDao dao) {
            asyncDao = dao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            asyncDao.insert(products[0]);
            return null;
        }
    }
}
