package com.mp2.mvproom.presenter.edit;

import android.app.Person;

public interface EditPresenter {

    void save(Person person);

    boolean validate(Person person);

    void getPersonAndPopulate(long id);

    void update(Person person);
}
