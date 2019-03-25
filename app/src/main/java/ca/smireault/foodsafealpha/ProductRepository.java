package ca.smireault.foodsafealpha;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private TypeDao typeDao;
    private LiveData<List<Type>> allTypes;
    private LiveData<List<Product>> allProducts;

    ProductRepository(Application application) {
        ProductRoomDatabase db = ProductRoomDatabase.getInstance(application);
        productDao = db.productDao();
        typeDao = db.typeDao();
        allProducts = productDao.getAllProducts();
        allTypes = typeDao.getAllTypes();
    }

    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
    LiveData<List<Type>> getAllTypes() { return allTypes; }

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
