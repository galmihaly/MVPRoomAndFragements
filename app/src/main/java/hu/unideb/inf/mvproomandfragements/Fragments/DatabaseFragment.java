package hu.unideb.inf.mvproomandfragements.Fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_database, container, false);
        dbFragRecycleView = view.findViewById(R.id.dbFragRecycleView);
        clearButton = view.findViewById(R.id.clearButton);

        dbFragRecycleView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ItemDecorator itemDecorator = new ItemDecorator(2);
        dbFragRecycleView.addItemDecoration(itemDecorator);

        room = Room.getInstance();
        databaseFragmentPresenter = new DatabaseFragmentPresenter(this);

        try {
            refreshUI();
        }catch (Exception e){
            Log.e("s", e.getMessage());
        }

        clearButton.setOnClickListener(view -> databaseFragmentPresenter.onClickClearButton());

        return view;
    }

    @Override
    public void refreshUI() {
        Log.e("g", String.valueOf(1));
        room.personEntityDAO().getAllPerson()
                .observe(getViewLifecycleOwner(), sliItems -> dbFragRecycleView.setAdapter(new DatabaseFragmentViewAdapter(sliItems)));

        Log.e("g", String.valueOf(2));
    }
}