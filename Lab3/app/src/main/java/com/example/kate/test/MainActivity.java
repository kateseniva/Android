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
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";
    public String message = "Користувач обрав: \n";

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

    public void openText(View view) {

        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.textViewRes);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            //text+=getFileStreamPath(FILE_NAME);
            textView.setText(text);
        } catch (IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void OnClickButtonOpen(View view) {
        openText(view);
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

                try {
                    // отрываем поток для записи
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                            openFileOutput(FILE_NAME, MODE_PRIVATE)));
                    // пишем данные
                    bw.write(strFinal);
                    // закрываем поток
                    bw.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
