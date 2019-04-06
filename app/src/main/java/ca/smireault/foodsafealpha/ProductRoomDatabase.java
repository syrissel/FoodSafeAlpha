package ca.smireault.foodsafealpha;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Product.class, Type.class}, version = 1, exportSchema = true)
public abstract class ProductRoomDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
    public abstract TypeDao typeDao();

    private static volatile ProductRoomDatabase INSTANCE;

    static ProductRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDatabase.class, "product_database")
                            .addCallback(populateDbCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback populateDbCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ProductDao productDao;
        private final TypeDao typeDao;

        PopulateDbAsync(ProductRoomDatabase db) {
            productDao = db.productDao();
            typeDao = db.typeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(android.os.Debug.isDebuggerConnected())
                android.os.Debug.waitForDebugger();

            productDao.deleteAll();
            typeDao.deleteAll();

            Type type = new Type("Meats");
            typeDao.insert(type);

            Product product = new Product("Beef", type.getId());
            productDao.insert(product);
            product = new Product("Roast Chicken", type.getId());
            productDao.insert(product);

            type = new Type("Veggies");
            typeDao.insert(type);

            product = new Product("Cauliflower", type.getId());
            productDao.insert(product);
            product = new Product("Asparagus", type.getId());
            productDao.insert(product);

            type = new Type("Snacks");
            typeDao.insert(type);

            product = new Product("Doritos", type.getId());
            productDao.insert(product);
            product = new Product("Chocolate Almonds", type.getId());
            productDao.insert(product);

            return null;
        }
    }
}
