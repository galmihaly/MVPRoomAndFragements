package hu.unideb.inf.mvproomandfragements.Fragments.Presenters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import hu.unideb.inf.mvproomandfragements.Database.Room;
import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IDatabaseFragmentPresenter;
import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IDatabaseFragmentView;
import hu.unideb.inf.mvproomandfragements.MainActivity.Helpers.DoWorkHelper;

public class DatabaseFragmentPresenter implements IDatabaseFragmentPresenter {

    private IDatabaseFragmentView iDatabaseFragmentView;
    private Room room = Room.getInstance();

    public DatabaseFragmentPresenter(IDatabaseFragmentView iDatabaseFragmentView) {
        this.iDatabaseFragmentView = iDatabaseFragmentView;
    }


    @Override
    public void onClickClearButton() {
        if(iDatabaseFragmentView == null) return;

        DoWorkHelper.DoWork(this::clearDB);
    }

    public void clearDB(){
        room.personEntityDAO().clearDB();
    }
}
