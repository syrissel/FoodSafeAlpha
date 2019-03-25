package ca.smireault.foodsafealpha;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Type {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Type(String name) {
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
}
