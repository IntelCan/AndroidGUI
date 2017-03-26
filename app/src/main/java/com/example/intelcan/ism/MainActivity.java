package com.example.intelcan.ism;

import android.content.Intent;
import android.icu.text.StringPrepParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String LICZBA_OCEN="liczba ocen";

    private Button przycisk, superButton, badButton;
    private EditText nameEdit, ratingCountEdit,lastNameEdit;
    private boolean ratingsCountOk,correctName, correctLastName;
    private String komunikat;
    private TextView averageResult,averageText;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        averageResult.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        findViewsById();
        addListeners();

    }

    public void findViewsById(){
        przycisk = (Button) findViewById(R.id.buttonOcena);
        superButton = (Button) findViewById(R.id.superButton);
        badButton = (Button) findViewById(R.id.badButton);
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        lastNameEdit = (EditText) findViewById(R.id.lastnameEdit);
        ratingCountEdit = (EditText) findViewById(R.id.ratingsCountEdit);
        averageText = (TextView) findViewById(R.id.averageText);
        averageResult = (TextView) findViewById(R.id.averageResult);
    }

    public void addListeners(){
        nameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus ) {
                zmianaFokusuLiczbyOcen(v, hasFocus, przycisk);
            }
        });

        lastNameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus ) {
                zmianaFokusuLiczbyOcen(v, hasFocus, przycisk);
            }
        });

        ratingCountEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus ) {
                zmianaFokusuLiczbyOcen(v, hasFocus, przycisk);
            }
        });
    }

    public void niePoszloPrzycisk(View v) {
        przycisk.setText("Aktywny");
        Toast.makeText(this, "Ups... gdzie przycisk ?",
                Toast.LENGTH_LONG).show();
    }

    private void checkRatingEdit(){
        ratingsCountOk = false;
        try {
            if (ratingCountEdit.getText().toString().equals(""))
                komunikat = getString(R.string.pusta_liczba_ocen);
            else if (Integer.parseInt(
                    ratingCountEdit.getText().toString()) <= 5)
                komunikat = getString(R.string.za_malo_ocen);
            else if (Integer.parseInt(
                    ratingCountEdit.getText().toString()) > 15)
                komunikat = getString(R.string.za_duzo_ocen);
            else
                ratingsCountOk = true;
        } catch (NumberFormatException e) {}
    }

    private void checkNameEdit(){
        correctName = false;
        try{
            if(nameEdit.getText().toString().equals(""))
                komunikat = "Nie wpisałeś imienia";
            else
                correctName = true;
        }
        catch (Exception e) {
            System.out.println("In Exception block.");
        }
    }

    private void checkLastNameEdit(){
        correctLastName = false;
        try{
            if(lastNameEdit.getText().toString().equals(""))
                komunikat = "Nie wpisałeś nazwiska";
            else
                correctName = true;
        }
        catch (Exception e) {
            System.out.println("In Exception block.");
        }
    }

    private void pokazPrzycisk() {
        if (ratingsCountOk && correctName)
            przycisk.setVisibility(Button.VISIBLE);
        else
           przycisk.setVisibility(Button.GONE);
    }

    private void zmianaFokusuLiczbyOcen(View v, boolean hasFocus, Button przycisk) {
        if (hasFocus)
            return;
        checkRatingEdit();
        checkLastNameEdit();
        checkNameEdit();
        if (!ratingsCountOk || !correctName) {
            Toast grzanka = Toast.makeText(this, komunikat,
                    Toast.LENGTH_SHORT);
            grzanka.show();
        }
        pokazPrzycisk();
    }

    public void fillRatings(View v){
        Intent intent = new Intent(this, FillRatingsActivity.class);
        intent.putExtra(LICZBA_OCEN, Integer.parseInt(ratingCountEdit.getText().toString()));
        startActivityForResult(intent,1);
    }

    public void superButton(View v){
        Toast.makeText(this, "Gites !!! Zdaane.... można iść na piwo",
                Toast.LENGTH_LONG).show();
        finish();
    }

    public void badButton(View v){
        Toast.makeText(this, "Bez spiny... są drugie terminy",
                Toast.LENGTH_LONG).show();
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        averageText.setVisibility(TextView.VISIBLE);
        averageResult.setVisibility(TextView.VISIBLE);

        // check that it is the SecondActivity with an OK result
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                String returnString = data.getStringExtra("result");
                Double aDouble = Double.parseDouble(returnString);

                // set text view with string
                averageResult.setText(returnString);
                przycisk.setVisibility(Button.GONE);

                if(aDouble >= 3)
                superButton.setVisibility(Button.VISIBLE);
                else
                    badButton.setVisibility(Button.VISIBLE);


            }
        }
    }

    //Aktywność za chwilę stanie się widoczna
    @Override
    protected void onStart() {
        super.onStart();
    }
    //Aktywność jest na pierwszym planie
    @Override
    protected void onResume()
    {
        super.onResume();
    }
    //Aktywność traci "focus". Zostanie zapauzowana.
    @Override
    protected void onPause()
    {
        super.onPause();
        //tutaj należy zwolnić zasoby i ew. zapisać istotne elementy stanu
        //ponieważ aktywność może zostać "zabita" przez system
    }
    //Aktywność nie jest widoczna. Została wstrzymana.
    @Override
    protected void onStop()
    {
        super.onStop();
        //tutaj należy zwolnić zasoby i ew. zapisać istotne elementy stanu
        //ponieważ aktywność może zostać "zabita" przez system
    }
    //Za chwilę aktywność zostanie zniszczona
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //tutaj należy zwolnić zasoby i ew. zapisać istotne elementy stanu
        //ponieważ aktywność może zostać "zabita" przez system
    }

}
