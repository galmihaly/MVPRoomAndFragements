package hu.unideb.inf.mvproomandfragements.Fragments;

import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import hu.unideb.inf.mvproomandfragements.Database.Models.PersonEntity;
import hu.unideb.inf.mvproomandfragements.Database.Room;
import hu.unideb.inf.mvproomandfragements.Fragments.Adapters.DatabaseFragmentViewAdapter;
import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IDatabaseFragmentView;
import hu.unideb.inf.mvproomandfragements.Fragments.Models.ItemDecorator;
import hu.unideb.inf.mvproomandfragements.Fragments.Presenters.DatabaseFragmentPresenter;
import hu.unideb.inf.mvproomandfragements.R;


public class DatabaseFragment extends BaseFragment implements IDatabaseFragmentView {

    private View view;
    private Room room;
    private RecyclerView dbFragRecycleView;
    private DatabaseFragmentPresenter databaseFragmentPresenter;
    private Button clearButton;
    private ItemDecorator itemDecorator;
    private DatabaseFragmentViewAdapter databaseFragmentViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_database, container, false);
        dbFragRecycleView = view.findViewById(R.id.dbFragRecycleView);
        clearButton = view.findViewById(R.id.clearButton);

        dbFragRecycleView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        dbFragRecycleView.setHasFixedSize(true);
        itemDecorator = new ItemDecorator(2);
        dbFragRecycleView.addItemDecoration(itemDecorator);

        // csak ez oldja meg azt, hogy ne írjon ki hibaüzenetet az Adapterkötés miatt
        // ezt meg kell majd később nézni
        databaseFragmentViewAdapter = new DatabaseFragmentViewAdapter(null);
        dbFragRecycleView.setAdapter(databaseFragmentViewAdapter);

        room = Room.getInstance();

        refreshUI();

        databaseFragmentPresenter = new DatabaseFragmentPresenter(this);
        clearButton.setOnClickListener(view -> databaseFragmentPresenter.onClickClearButton());

        return view;
    }

    @Override
    public void refreshUI() {

        try {
            room.personEntityDAO().getAllPerson()
                    .observe(getViewLifecycleOwner(),
                            personEntities -> dbFragRecycleView.setAdapter(new DatabaseFragmentViewAdapter(personEntities))
                    );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearRoomDatabase() {
        try {
            room.personEntityDAO().clearDB();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}