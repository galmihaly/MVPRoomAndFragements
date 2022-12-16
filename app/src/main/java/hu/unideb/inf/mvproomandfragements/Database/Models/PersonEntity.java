package hu.unideb.inf.mvproomandfragements.Database.Models;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PeopleList")
public class PersonEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String firstName;
    private String lastName;

    public PersonEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("%d: %s %s", this.id, this.firstName, this.lastName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
