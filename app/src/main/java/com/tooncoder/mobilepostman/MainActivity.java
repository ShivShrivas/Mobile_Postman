package com.tooncoder.mobilepostman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<String, String> keyValuePairs = new HashMap<>();
    private LinearLayout linearLayout;
    Button addPairBtn;
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addPairBtn=findViewById(R.id.addPairBtn);
        tableLayout=findViewById(R.id.tableLayout);
        addPairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddKeyValueDialog();
            }
        });

     }
    private void showAddKeyValueDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.key_value_dialog, null);

        final EditText keyEditText = dialogView.findViewById(R.id.keyEditText);
        final EditText valueEditText = dialogView.findViewById(R.id.valueEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Key-Value Pair");
        builder.setView(dialogView);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String key = keyEditText.getText().toString();
                String value = valueEditText.getText().toString();

                if (!key.isEmpty() && !value.isEmpty()) {
                    keyValuePairs.put(key, value);
                    addKeyValuePairToTable(key, value);
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void addKeyValuePairToTable(String key, String value) {
        TableRow row = new TableRow(this);
        TextView keyTextView = new TextView(this);
        TextView valueTextView = new TextView(this);

        keyTextView.setText(key);
        valueTextView.setText(value);

        row.addView(keyTextView);
        row.addView(valueTextView);

        tableLayout.addView(row);
    }
}
