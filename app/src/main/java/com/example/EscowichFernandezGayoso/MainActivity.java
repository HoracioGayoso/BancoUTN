package com.example.EscowichFernandezGayoso;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.EscowichFernandezGayoso.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Button buttonConstituir;
    private String resultadoPlazo ="", resultadoCapital="";
    private EditText editTextNombre,editTextApellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Spinner spinnerMonedas = binding.spinnerMonedas;
        spinnerMonedas.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.datos)));
        Button buttonSimular = binding.buttonSimular;
        buttonConstituir = binding.buttonConstituir;
        buttonConstituir.setEnabled(false);

        editTextNombre = binding.editTextNombre;
        editTextApellido = binding.editTextApellido;

        buttonSimular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSimular = new Intent(MainActivity.this,simularPlazoFijoActivity.class);
                startActivityForResult(intentSimular,0);
            }
        });
        buttonConstituir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Felicitaciones "+(editTextNombre.getText().toString())+" "+(editTextApellido.getText().toString()));
                builder.setMessage("Tu plazo fijo de "+resultadoCapital+" "+spinnerMonedas.getSelectedItem().toString()+" por "+ resultadoPlazo+" días ha sido constituido");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (0) : {
                if (resultCode == Activity.RESULT_OK) {
                    buttonConstituir.setEnabled(true);
                    resultadoPlazo = data.getStringExtra("Plazo");
                    resultadoCapital = data.getStringExtra("Capital");

                }
                break;
            }
        }
    }

}