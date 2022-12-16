package hu.unideb.inf.mvproomandfragements.Fragments;

import androidx.fragment.app.Fragment;

import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IFragmentNavigationPresenter;
import hu.unideb.inf.mvproomandfragements.Fragments.Interfaces.IFragmentNavigationView;

public abstract class BaseFragment extends Fragment implements IFragmentNavigationView {

    protected IFragmentNavigationPresenter navigationPresenter;
    @Override
    public void atachPresenter(IFragmentNavigationPresenter presenter) {
        navigationPresenter = presenter;
    }
}
