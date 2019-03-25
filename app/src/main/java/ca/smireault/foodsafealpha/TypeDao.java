package ca.smireault.foodsafealpha;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TypeDao {

    @Insert
    void insert(Type type);

    @Query("SELECT * FROM type ORDER BY name ASC")
    LiveData<List<Type>> getAllTypes();

    @Query("DELETE FROM type")
    void deleteAll();
}
