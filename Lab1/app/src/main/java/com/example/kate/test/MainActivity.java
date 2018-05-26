package com.example.kate.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupCours;
    private RadioGroup radioGroupFaculty;
    private RadioButton radioButtonCours;
    private RadioButton radioButtonFaculty;
    private TextView selection;
    private Button btnOk;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        addListenerOnButton();


    }

    public void addListenerOnButton() {

        selection = (TextView) findViewById(R.id.selection);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        radioGroupCours = (RadioGroup) findViewById(R.id.radiogrpCours);
        radioGroupFaculty = (RadioGroup) findViewById(R.id.radiogrpFaculty);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedCoursId = radioGroupCours.getCheckedRadioButtonId();
                int selectedFacultyId = radioGroupFaculty.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButtonCours = (RadioButton) findViewById(selectedCoursId);
                radioButtonFaculty = (RadioButton) findViewById(selectedFacultyId);

                String strCours = (String)radioButtonCours.getText();
                String strFaculty = (String)radioButtonFaculty.getText();
                String strResult = getResources().getString(R.string.madeSelect);
                String strFinal = String.format(strResult, strCours, strFaculty);

                selection.setText(strFinal);



            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                radioGroupCours.clearCheck();
                radioGroupFaculty.clearCheck();

                String strMakeSelect = getResources().getString(R.string.makeSelect);
                selection.setText(strMakeSelect);
                //radioSexButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }
}
