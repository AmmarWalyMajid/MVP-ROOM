package com.mp2.mvproom.view.edit;

import android.app.Person;

public interface EditView {

    void showErrorMessage(int field);

    void clearPreErrors();

    void close();

    void populate(Person person);
}
