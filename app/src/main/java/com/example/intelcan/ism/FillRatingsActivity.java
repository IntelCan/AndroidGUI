package com.example.intelcan.ism;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.intelcan.ism.MainActivity.LICZBA_OCEN;

/**
 * Created by Arek on 25.03.2017.
 */

public class FillRatingsActivity extends AppCompatActivity {

    public final static String OCENA="ocena";
    private ArrayList<RatingsModel> mDane;
    private ListView mListaOcen;
    private int mLiczbaOcen;
    private double average = 0;
    private String stringResult;
    private double suma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_ratings);

        if (savedInstanceState == null) {
            Bundle dodatkoweInfo = getIntent().getExtras();
            mLiczbaOcen =
                    dodatkoweInfo.getInt(LICZBA_OCEN);
        } else
            mLiczbaOcen = 5;

        mDane = new ArrayList<RatingsModel>();
        for (int i = 0; i < mLiczbaOcen; i++)
            mDane.add(new RatingsModel("ocena " + (i + 1)));

        mListaOcen = (ListView) findViewById(R.id.listView);
        InteractiveArrayAdapter adapter =
                new InteractiveArrayAdapter(this, mDane);
        mListaOcen.setAdapter(adapter);
    }

    private void zliczSrednia(RadioGroup grupaOceny) {
            mDane.get(1);
    }

    public void ready(View v){
        calculateAverage();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",stringResult);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    public String calculateAverage(){
        for(int i=0;i<mLiczbaOcen;i++){
            suma += mDane.get(i).getRating();
        }
        average =suma/((double)mLiczbaOcen);
        stringResult = Double.toString(average);
        return stringResult;
    }


}
