package com.mp2.mvproom.view.edit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mp2.mvproom.R;
import com.mp2.mvproom.database.AppDatabase;
import com.mp2.mvproom.presenter.edit.EditPresenterImp;
import com.mp2.mvproom.utils.Constants;

public class EditActivity extends AppCompatActivity implements EditView{

    private EditPresenterImp presenterImp;
    private EditText mEditName, mEditAddress, mEditEmail, mEditPhone;
    private TextInputLayout mTextInputLayoutName, mTextInputLayoutAddress, mTextInputLayoutEmail, mTextInputLayoutPhone;

    private Person person;
    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
        checkMode();
    }

    private void init() {

        person = new Person();

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        presenterImp = new EditPresenterImp(this, db.personModel());

        mEditName = findViewById(R.id.et_name);
        mEditAddress = findViewById(R.id.et_address);
        mEditEmail = findViewById(R.id.et_email);
        mEditPhone = findViewById(R.id.et_phone);
        mTextInputLayoutName = findViewById(R.id.ti_name);
        mTextInputLayoutAddress = findViewById(R.id.ti_address);
        mTextInputLayoutEmail = findViewById(R.id.ti_email);
        mTextInputLayoutPhone = findViewById(R.id.ti_phone);
        FloatingActionButton mFabSave = findViewById(R.id.fabSave);
        mFabSave.setImageResource(editMode ? R.drawable.ic_refresh : R.drawable.ic_done);

        mFabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.name = mEditName.getText().toString();
                person.address = mEditAddress.getText().toString();
                person.email = mEditEmail.getText().toString();
                person.phone = mEditPhone.getText().toString();

                boolean valid = presenterImp.validate(person);

                if (!valid) return;

                if (editMode) {
                    presenterImp.update(person);
                } else {
                    presenterImp.save(person);
                }
            }
        });
    }

    private void checkMode() {

        if (getIntent().getExtras() != null) {
            person.id = getIntent().getLongExtra(Constants.PERSON_ID, 0);
            editMode = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (editMode) {
            presenterImp.getPersonAndPopulate(person.id);
        }
    }

    @Override
    public void showErrorMessage(int field) {

        if (field == Constants.FIELD_NAME) {
            mTextInputLayoutName.setError(getString(R.string.invalid_name));
        } else if (field == Constants.FIELD_EMAIL) {
            mTextInputLayoutEmail.setError(getString(R.string.invalid_email));
        } else if (field == Constants.FIELD_PHONE) {
            mTextInputLayoutPhone.setError(getString(R.string.invalid_phone));
        } else if (field == Constants.FIELD_ADDRESS) {
            mTextInputLayoutAddress.setError(getString(R.string.invalid_address));
        }

    }

    @Override
    public void clearPreErrors() {

        mTextInputLayoutName.setErrorEnabled(false);
        mTextInputLayoutEmail.setErrorEnabled(false);
        mTextInputLayoutPhone.setErrorEnabled(false);
        mTextInputLayoutAddress.setErrorEnabled(false);
    }

    @Override
    public void close() {

        finish();

    }

    @Override
    public void populate(Person person) {

        this.person = person;
        mEditName.setText(person.name);
        mEditAddress.setText(person.address);
        mEditEmail.setText(person.email);
        mEditPhone.setText(person.phone);

    }
}