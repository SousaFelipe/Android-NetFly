package net.kingbets.cambista.utils;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import net.kingbets.cambista.model.local.apostas.Single;
import net.kingbets.cambista.view.dialogs.CriaCupomDialog;

import java.text.NumberFormat;


public class MoneyTextWatcher implements TextWatcher {



    private CriaCupomDialog parent;
    private EditText editText;

    private String lastAmount = "";



    public MoneyTextWatcher(CriaCupomDialog parent, EditText editText) {
        super();
        this.parent = parent;
        this.editText = editText;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if( ! s.toString().equals(lastAmount)) {

            editText.removeTextChangedListener(this);

            String clean  = s.toString().replaceAll("[R$,.]", "");
            double parsed = (Double.parseDouble(clean) / 100);

            String formatted = NumberFormat.getCurrencyInstance().format(
                    (parsed > Config.LIMITE_VALOR) ? Config.LIMITE_VALOR : parsed
            );

            lastAmount = formatted;

            editText.setText(formatted);
            editText.setSelection(formatted.length());
            editText.addTextChangedListener(this);
        }
    }


    @Override
    public void afterTextChanged(Editable s) {

        Single single = Single.instance();
        String clean = s.toString().replaceAll("[R$,.]", "");

        single.setValorApostado(Double.parseDouble(clean) / 100);
        single.refresh();
        parent.refresh();
    }
}
