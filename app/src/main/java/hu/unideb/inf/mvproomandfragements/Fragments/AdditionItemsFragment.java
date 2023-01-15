package hu.unideb.inf.mvproomandfragements.Fragments;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import hu.unideb.inf.mvproomandfragements.Database.Models.PersonEntity;
import hu.unideb.inf.mvproomandfragements.Database.Room;
import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IAdditionItemsFragmentView;
import hu.unideb.inf.mvproomandfragements.Fragments.Presenters.AdditionItemsFragmentPresenter;
import hu.unideb.inf.mvproomandfragements.R;


public class AdditionItemsFragment extends BaseFragment implements IAdditionItemsFragmentView {

    private View view;

    private AdditionItemsFragmentPresenter additionItemsFragmentPresenter;
    private Room room;

    private Button addButton;
    private EditText editTextFirstName;
    private EditText editTextLastName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_addition_items, container, false);

        addButton = view.findViewById(R.id.addButton);
        editTextFirstName = view.findViewById(R.id.editTextFirstName);
        editTextLastName = view.findViewById(R.id.editTextLastName);

        room = Room.getInstance();

        additionItemsFragmentPresenter = new AdditionItemsFragmentPresenter(this);

        addButton.setOnClickListener(view -> additionItemsFragmentPresenter.onAddItemButton(
                new PersonEntity(editTextLastName.getText().toString(), editTextFirstName.getText().toString())
        ));

        return view;
    }

    @Override
    public void addPersonToRoomDatabase(PersonEntity personEntity) {

        try {
            room.personEntityDAO().setPerson(personEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}