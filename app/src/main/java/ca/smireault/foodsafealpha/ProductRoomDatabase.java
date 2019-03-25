package ca.smireault.foodsafealpha;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Product.class, Type.class}, version = 3, exportSchema = true)
public abstract class ProductRoomDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
    public abstract TypeDao typeDao();

    private static volatile ProductRoomDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Type` (`id` INTEGER NOT NULL, `name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE product;");
            database.execSQL("CREATE TABLE Product (id INTEGER NOT NULL, name TEXT, type_id INTEGER NOT NULL," +
                    "PRIMARY KEY (id), " +
                    "CONSTRAINT fk_type_id FOREIGN KEY (type_id) REFERENCES Type(id));");

        }
    };

    static ProductRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDatabase.class, "product_database")
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
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
            productDao.deleteAll();
            typeDao.deleteAll();
//            Product product = new Product("Beef");
//            productDao.insert(product);
//            product = new Product("Chips");
//            productDao.insert(product);
            Type type = new Type("Meats");
            typeDao.insert(type);
            type = new Type("Veggies");
            typeDao.insert(type);
            type = new Type("Snacks");
            typeDao.insert(type);
            return null;
        }
    }
}
