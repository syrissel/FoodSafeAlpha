package ca.smireault.foodsafealpha;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity
public class Type {

    @PrimaryKey
    @NonNull
    private int id;
    private static int id_increment = 0;
    private String name;

    public Type(String name) {
        this.id = autoIncrement();
        this.name = name;
    }

    private int autoIncrement() {
        return id_increment++;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
}
