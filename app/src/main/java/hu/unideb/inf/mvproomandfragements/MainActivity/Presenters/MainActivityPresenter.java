package hu.unideb.inf.mvproomandfragements.MainActivity.Presenters;

import hu.unideb.inf.mvproomandfragements.Fragments.BaseFragment;
import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IFragmentNavigationPresenter;
import hu.unideb.inf.mvproomandfragements.MainActivity.Helpers.DoWorkHelper;
import hu.unideb.inf.mvproomandfragements.MainActivity.Interfaces.IMainActivityPresenter;
import hu.unideb.inf.mvproomandfragements.MainActivity.Interfaces.IMainActivityView;
import hu.unideb.inf.mvproomandfragements.MainActivityView;

public class MainActivityPresenter implements IMainActivityPresenter, IFragmentNavigationPresenter {

    private IMainActivityView iMainActivityView;

    public MainActivityPresenter(IMainActivityView mainActivityView) {
        this.iMainActivityView = mainActivityView;
    }

    @Override
    public void addFragment(BaseFragment baseFragment) {

        if(iMainActivityView == null) return;
        DoWorkHelper.DoWork(baseFragment, this::backEndTask);
    }

    public void backEndTask(BaseFragment baseFragment){
        iMainActivityView.setFragment(baseFragment);
    }
}
