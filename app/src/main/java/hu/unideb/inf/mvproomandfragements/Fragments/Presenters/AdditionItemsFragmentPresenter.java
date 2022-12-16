package hu.unideb.inf.mvproomandfragements.Fragments.Presenters;

import hu.unideb.inf.mvproomandfragements.Database.Models.PersonEntity;
import hu.unideb.inf.mvproomandfragements.Database.Room;
import hu.unideb.inf.mvproomandfragements.Fragments.BaseFragment;
import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IAdditionItemsFragmentPresenter;
import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IAdditionItemsFragmentView;
import hu.unideb.inf.mvproomandfragements.MainActivity.Helpers.DoWorkHelper;

public class AdditionItemsFragmentPresenter implements IAdditionItemsFragmentPresenter {

    private IAdditionItemsFragmentView iAdditionItemsFragmentView;
    private Room room = Room.getInstance();

    public AdditionItemsFragmentPresenter(IAdditionItemsFragmentView iAdditionItemsFragmentView) {
        this.iAdditionItemsFragmentView = iAdditionItemsFragmentView;
    }

    @Override
    public void onAddItemButton(PersonEntity personEntity) {
        if(iAdditionItemsFragmentView == null) return;

        DoWorkHelper.DoWork(personEntity, this::backEndTask);
    }

    public void backEndTask(PersonEntity personEntity){
        room.personEntityDAO().setPerson(personEntity);
    }
}
