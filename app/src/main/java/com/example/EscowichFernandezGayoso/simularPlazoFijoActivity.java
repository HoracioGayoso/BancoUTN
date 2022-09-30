package com.example.EscowichFernandezGayoso;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.EscowichFernandezGayoso.databinding.LayoutSimularPlazoFijoActivityBinding;

public class simularPlazoFijoActivity extends AppCompatActivity {
    private LayoutSimularPlazoFijoActivityBinding binding;
    private float plazo = 0;
    private String stringPlazo = (Integer.toString(0)) + " dias";
    private SeekBar seekBarPlazo;
    private TextView textViewPlazo;
    private EditText editTextTasaNominal, editTextTasaEfectiva, editTextCapital;
    private TextView textViewSimPlazo,textViewSimCapital,textViewSimInteresesG,textViewSimMontoTotal,textViewSimMontoTotalAnual;
    private TextView textViewSimPlazoValor,textViewSimCapitalValor,textViewSimInteresesGValor,textViewSimMontoTotalValor,textViewSimMontoTotalAnualValor;
    private Button buttonConfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutSimularPlazoFijoActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        textViewPlazo = binding.textViewPlazo;
        textViewPlazo.setText(stringPlazo);
        seekBarPlazo = binding.seekBarPlazo;

        editTextCapital = binding.editTextCapital;
        editTextTasaEfectiva = binding.editTextTasaEfectiva;
        editTextTasaNominal = binding.editTextTasaNominal;

        textViewSimPlazoValor = binding.textViewSimPlazoValor;
        textViewSimPlazo = binding.textViewSimPlazo;
        textViewSimCapital = binding.textViewSimCapital;
        textViewSimInteresesG = binding.textViewSimInteresesG;
        textViewSimMontoTotal = binding.textViewSimMontoTotal;
        textViewSimMontoTotalAnual = binding.textViewSimMontoTotalAnual;

        textViewSimCapitalValor = binding.textViewSimCapitalValor;
        textViewSimInteresesGValor = binding.textViewSimInteresesGValor;
        textViewSimMontoTotalValor = binding.textViewSimMontoTotalValor;
        textViewSimMontoTotalAnualValor = binding.textViewSimMontoTotalAnualValor;

        buttonConfirmar = binding.buttonConfirmar;
        buttonConfirmar.setEnabled(false);
        setearListeners(editTextTasaNominal,editTextTasaEfectiva,editTextCapital,seekBarPlazo);
        setContentView(view);

        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Capital",editTextCapital.getText().toString());
                resultIntent.putExtra("Plazo",String.valueOf(seekBarPlazo.getProgress()*30));
                setResult(simularPlazoFijoActivity.RESULT_OK, resultIntent);
                finish();
            }
        });

    }
    protected void calcular(){
        float Capital, TasaNominal,interesesGanados,MontoTotal,MontoTotalAnual;
        if(camposInvalidos()){
            buttonConfirmar.setEnabled(false);
            textViewSimCapitalValor.setText("0");
            textViewSimInteresesGValor.setText("0");
            textViewSimMontoTotalValor.setText("0");
            textViewSimMontoTotalAnualValor.setText("0");
        }
        else{
            Capital = Float.parseFloat(editTextCapital.getText().toString());
            TasaNominal = Float.parseFloat(editTextTasaNominal.getText().toString());
            interesesGanados = Capital *(TasaNominal/100)*(plazo/12);
            MontoTotal = Capital + interesesGanados;
            MontoTotalAnual = Capital + (Capital*(TasaNominal/100));
            textViewSimCapitalValor.setText(editTextCapital.getText());
            textViewSimInteresesGValor.setText(String.valueOf(interesesGanados));
            textViewSimMontoTotalValor.setText(String.valueOf(MontoTotal));
            textViewSimMontoTotalAnualValor.setText(String.valueOf(MontoTotalAnual));
            buttonConfirmar.setEnabled(true);
        }
    }
    protected boolean camposInvalidos(){
        if( editTextCapital.getText().toString().equals("") ||
            editTextTasaEfectiva.getText().toString().equals("") ||
            editTextTasaNominal.getText().toString().equals("") ||
            seekBarPlazo.getProgress()== 0){
            return true;
        }
        else
        return false;
    }
    protected void setearListeners(EditText textNominal,EditText textEfectiva,EditText textCapital,SeekBar seekPlazoF){
        textNominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcular();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textEfectiva.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcular();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textCapital.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcular();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        seekBarPlazo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                plazo = seekBar.getProgress();
                stringPlazo = (Integer.toString((int) plazo*30)) + "dias";
                textViewPlazo.setText(stringPlazo);
                textViewSimPlazoValor.setText(stringPlazo);
                calcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
