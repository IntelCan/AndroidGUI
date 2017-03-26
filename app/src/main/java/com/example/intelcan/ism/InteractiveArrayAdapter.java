package com.example.intelcan.ism;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arek on 25.03.2017.
 */

public class InteractiveArrayAdapter extends ArrayAdapter<RatingsModel> {
    private List<RatingsModel> listaOcen;
    private Activity context;

    public InteractiveArrayAdapter(Activity context,
                                   List<RatingsModel> listaOcen) {
        super(context, R.layout.row_ratings, listaOcen);
        this.context = context;
        this.listaOcen = listaOcen;
    }
    @Override
    public View getView(int numerWiersza,
                        View widokDoRecyklingu,
                        ViewGroup parent) {
        View widok = null;
        if (widokDoRecyklingu == null) {
            LayoutInflater pompka = context.getLayoutInflater();
            widok = pompka.inflate(R.layout.row_ratings, null);
            RadioGroup grupaOceny =
                    (RadioGroup) widok.
                            findViewById(R.id.radioGroup);
            grupaOceny.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(
                                RadioGroup grupaOceny,
                                int idWybranegoButtona)
                        {
                            aktualizujModelOceny(grupaOceny,
                                    idWybranegoButtona);
                        }
                    });
            grupaOceny.setTag(listaOcen.get(numerWiersza));
        } else {
            widok = widokDoRecyklingu;
            RadioGroup grupaOceny =
                    (RadioGroup) widok.
                            findViewById(R.id.radioGroup);
            grupaOceny.setTag(listaOcen.get(numerWiersza));
        }
        TextView etykieta =
                (TextView) widok.
                        findViewById(R.id.ratingsView);
        etykieta.setText(
                listaOcen.get(numerWiersza).getName());
        RadioGroup grupaOceny = (RadioGroup) widok.
                findViewById(R.id.radioGroup);
        ustawOcene(grupaOceny, numerWiersza);
        return widok;
    }
    private void ustawOcene(RadioGroup grupaOceny, int numerWiersza) {
        switch (listaOcen.get(numerWiersza).getRating()) {
            case 2:
                grupaOceny.check(R.id.radioButton2);
                break;
            case 3:
                grupaOceny.check(R.id.radioButton3);
                break;
            case 4:
                grupaOceny.check(R.id.radioButton4);
                break;
            case 5:
                grupaOceny.check(R.id.radioButton5);
                break;
        }
    }
    private void aktualizujModelOceny(RadioGroup grupaOceny,int idWybranegoButtona) {
        RatingsModel element = (RatingsModel) grupaOceny.getTag();
        switch (idWybranegoButtona) {
            case R.id.radioButton2:
                element.setRating(2);
                break;
            case R.id.radioButton3:
                element.setRating(3);
                break;
            case R.id.radioButton4:
                element.setRating(4);
                break;
            case R.id.radioButton5:
                element.setRating(5);
                break;
        }
    }

}
