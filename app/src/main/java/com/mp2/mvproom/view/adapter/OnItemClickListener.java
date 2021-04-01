package com.mp2.mvproom.view.adapter;

import android.app.Person;

public interface OnItemClickListener {

    void clickItem(Person person);

    void clickLongItem(Person person);
}
