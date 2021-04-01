package com.mp2.mvproom.view.main;

import android.app.Person;

import java.util.List;

public interface MainView {

    void showAddPerson();

    void setPersons(List<Person> persons);

    void showEditScreen(long id);

    void showDeleteConfirmDialog(Person person);

    void showEmptyMessage();
}
