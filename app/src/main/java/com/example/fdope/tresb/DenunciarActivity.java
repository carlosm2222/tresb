package com.example.fdope.tresb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fdope.tresb.DB.ConsultasProductos;

import static com.example.fdope.tresb.R.id.motivo1;
import static com.example.fdope.tresb.R.id.motivo4;

public class DenunciarActivity extends AppCompatActivity {
    private Button denunciar;
    private Button b;
    private int idEvento;
    private RadioGroup radio;
    private RadioButton radioBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denunciar);
        Bundle bundle = getIntent().getExtras();
        idEvento = bundle.getInt("idEvento");
        b = (Button) findViewById(R.id.Denunciar);
        radio = (RadioGroup) findViewById(R.id.radio);
        b.setEnabled(false);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                b.setEnabled(true);
            }
        });
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radio.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioBoton = (RadioButton) findViewById(selectedId);

                if (ConsultasProductos.agregarDenuncia(idEvento,(String)radioBoton.getText())==true) {
                    Toast.makeText(DenunciarActivity.this, "Denuncia enviada", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (ConsultasProductos.agregarDenuncia(idEvento,(String)radioBoton.getText())==false){
                    Toast.makeText(DenunciarActivity.this, "No se pudo enviar su denuncia.", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

        });

    }
}
