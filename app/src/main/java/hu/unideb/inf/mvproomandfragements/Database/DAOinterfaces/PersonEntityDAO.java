package hu.unideb.inf.mvproomandfragements.Database.DAOinterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.unideb.inf.mvproomandfragements.Database.Models.PersonEntity;

@Dao
public interface PersonEntityDAO {

    @Query("SELECT id, firstName, lastName FROM PeopleList")
    LiveData<List<PersonEntity>> getAllPerson();

    @Insert
    void setPerson(PersonEntity personEntity);

    @Query("Delete from PeopleList")
    public void clearDB();

}
