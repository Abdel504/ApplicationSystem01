package com.example.applicatie_system_pass_096;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    // Declaratie van variabelen voor UI-elementen en opslag
    private EditText etPassword;
    private TextView tvResult;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Zorgt dat de inhoud niet overlapt met systeem­elementen (zoals statusbalk)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Koppel de elementen uit de layout aan de variabelen
        etPassword = findViewById(R.id.etPassword);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLoad = findViewById(R.id.btnLoad);
        tvResult = findViewById(R.id.tvResult);

        // Labels op de knoppen
        btnSave.setText("Inloggen");
        btnLoad.setText("Wachtwoord ophalen");

        // SharedPreferences-object verkrijgen (geen gevoelige data opslaan)
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Wanneer op "Inloggen" wordt gedrukt > inloggen zonder wachtwoord op te slaan
        btnSave.setOnClickListener(v -> {
            String password = etPassword.getText().toString();
            if (password.isEmpty()) {
                tvResult.setText("Geen wachtwoord ingevoerd.");
            } else {
                tvResult.setText("Ingelogd (wachtwoord niet opgeslagen).");
            }
            Toast.makeText(this, "Inloggen verwerkt", Toast.LENGTH_SHORT).show();
        });

        // Wanneer op "Wachtwoord ophalen" wordt gedrukt > er is geen wachtwoord opgeslagen
        btnLoad.setOnClickListener(v -> {
            tvResult.setText("Er is geen wachtwoord opgeslagen.");
        });
    }
}
