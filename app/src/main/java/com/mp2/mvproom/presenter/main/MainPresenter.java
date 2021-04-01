package com.mp2.mvproom.presenter.main;

import android.app.Person;

public interface MainPresenter {

    void addNewPerson();

    void result(int requestCode, int resultCode);

    void populatePeople();

    void openEditScreen(Person person);

    void openConfirmDeleteDialog(Person person);

    void delete(long personId);


}
