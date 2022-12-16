package hu.unideb.inf.mvproomandfragements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import hu.unideb.inf.mvproomandfragements.Database.Room;
import hu.unideb.inf.mvproomandfragements.Fragments.AdditionItemsFragment;
import hu.unideb.inf.mvproomandfragements.Fragments.BaseFragment;
import hu.unideb.inf.mvproomandfragements.Fragments.DatabaseFragment;
import hu.unideb.inf.mvproomandfragements.MainActivity.Interfaces.IMainActivityView;
import hu.unideb.inf.mvproomandfragements.MainActivity.Presenters.MainActivityPresenter;

public class MainActivityView extends AppCompatActivity implements IMainActivityView {

    //Buttons
    private Button elementsButton;
    private Button addItemButton;

    //Database
    private Room room;

    //Presenter
    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elementsButton = findViewById(R.id.elementsButton);
        addItemButton = findViewById(R.id.addItemButton);

        mainActivityPresenter = new MainActivityPresenter(this);

        setFragment(new DatabaseFragment());

        room = Room.getDatabase(this);

        elementsButton.setOnClickListener(view -> mainActivityPresenter.addFragment(new DatabaseFragment()));
        addItemButton.setOnClickListener(view -> mainActivityPresenter.addFragment(new AdditionItemsFragment()));

    }

    @Override
    public void setFragment(BaseFragment baseFragment) {
        baseFragment.atachPresenter(mainActivityPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentConstraintLayout, baseFragment)
                .commit();
    }
}