package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NameEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_edit);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String playerNameX = extras.getString("player_x");
            String playerNameY = extras.getString("player_y");
            EditText playerNameXTextEdit = findViewById(R.id.playerNameXTextEdit);
            playerNameXTextEdit.setText(playerNameX);
            EditText playerNameYTextEdit = findViewById(R.id.playerNameYTextEdit);
            playerNameYTextEdit.setText(playerNameY);
        }
        Button setNameButton = findViewById(R.id.set_name_button);
        setNameButton.setOnClickListener(e -> closeAndSave());
    }

    private void closeAndSave() {
        EditText playerNameXTextEdit = findViewById(R.id.playerNameXTextEdit);
        EditText playerNameYTextEdit = findViewById(R.id.playerNameYTextEdit);
        Intent playerNameIntent = new Intent(this, MainActivity.class);
        playerNameIntent.putExtra("player_x", playerNameXTextEdit.getText().toString());
        playerNameIntent.putExtra("player_y", playerNameYTextEdit.getText().toString());
        setResult(Activity.RESULT_OK, playerNameIntent);
        finish();
    }

}