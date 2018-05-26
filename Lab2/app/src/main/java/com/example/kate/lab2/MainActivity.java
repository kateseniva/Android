package com.example.kate.lab2;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.kate.lab2.R;

public class MainActivity extends FragmentActivity {

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private TextView selection;
    private RadioGroup radioGroupCours;
    private RadioGroup radioGroupFaculty;
    private RadioButton radioButtonCours;
    private RadioButton radioButtonFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        initOneFragment();
        initTwoFragment();

    }

    private void initOneFragment() {
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, oneFragment, OneFragment.TAG);
        transaction.commit();
    }

    private void initTwoFragment() {
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, twoFragment, TwoFragment.TAG);
        transaction.commit();


    }

    public void OnCickFragment(View view) {

        selection = (TextView) findViewById(R.id.selection);
        radioGroupCours = (RadioGroup) findViewById(R.id.radiogrpCours);
        radioGroupFaculty = (RadioGroup) findViewById(R.id.radiogrpFaculty);
      //  transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.btnOk:
                //  if(manager.findFragmentByTag(OneFragment.TAG) != null) {

                int selectedCoursId = radioGroupCours.getCheckedRadioButtonId();
                int selectedFacultyId = radioGroupFaculty.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButtonCours = (RadioButton) findViewById(selectedCoursId);
                radioButtonFaculty = (RadioButton) findViewById(selectedFacultyId);

                String strCours = (String) radioButtonCours.getText();
                String strFaculty = (String) radioButtonFaculty.getText();
                String strResult = getResources().getString(R.string.madeSelect);
                String strFinal = String.format(strResult, strCours, strFaculty);

                selection.setText(strFinal);

                break;
            case R.id.btnCancel:
                    radioGroupCours.clearCheck();
                    radioGroupFaculty.clearCheck();
                    selection.setText("");
                    break;
                }
        }
    }

