package com.example.teste_preferencias;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button buttonSave= null;
    private EditText editText = null;
    private SharedPreferences myPrefs=null;

    TextView textView= null;
    Button button=null;
    boolean chave = true;



    private void saveName(){
        buttonSave.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                SharedPreferences.Editor ePrefs = myPrefs.edit();
                ePrefs.putString("nome",MainActivity.this.editText.getText().toString());
                ePrefs.commit();
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        buttonSave = this.findViewById(R.id.button);
        editText = this.findViewById(R.id.editTextText);
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String name = myPrefs.getString("nome","");
        if (name!=null){
            editText.setText(name);
        }

       this.saveName();

        // INTERNACIOLiZAÇÃO

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button2);
        textView.setText(R.string.texto_inter);

        Resources resources = getResources();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chave) {
                    setLocale(resources, "pt_BR");

                    chave = false;
                }else {
                    setLocale(resources, "en");
                    chave=true;
                }
                textView.setText(R.string.texto_inter);
                button.setText(R.string.botao);
            }
        });
    }

    private void setLocale(Resources resources, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}