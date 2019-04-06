package ca.smireault.foodsafealpha;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(foreignKeys = {@ForeignKey(entity = Type.class,
        parentColumns = "id",
        childColumns = "type_id")})
public class Product {
    @PrimaryKey
    @NonNull
    private int id;
    private static int id_increment = 0;
    private String name;
    @NonNull
    private int type_id;

    public Product(String name, int type_id) {
        this.id = autoIncrement();
        this.name = name;
        this.type_id = type_id;
    }

    private int autoIncrement() {
        return id_increment++;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public int getType_id() { return type_id; }
    public void setType_id(int type_id) { this.type_id = type_id; }

    public String getName() {
        return this.name;
    }
}
