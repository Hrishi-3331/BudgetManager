package com.hrishi_3331.hrishi_studio.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    LinearLayout divider, updator, manager, updator2, divider2, manager2, noalert;
    Button view_btn;
    String[] months;
    String[] years;
    EditText limit;
    SharedPreferences preferences;
    CoordinatorLayout cord;
    Spinner year;
    Spinner month;
    String selectedMonth;
    String selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");

        months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        years = new String[]{"2018", "2019", "2020", "2021", "2022"};

        divider = (LinearLayout)findViewById(R.id.divider);
        updator = (LinearLayout)findViewById(R.id.updater);
        manager = (LinearLayout)findViewById(R.id.manager);

        divider2 = (LinearLayout)findViewById(R.id.divider2);
        updator2 = (LinearLayout)findViewById(R.id.updater2);
        manager2 = (LinearLayout)findViewById(R.id.manager2);
        view_btn = (Button)findViewById(R.id.view_btn);
        cord = (CoordinatorLayout)findViewById(R.id.cord);

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (updator.getVisibility() == View.GONE) {
                    updator.setVisibility(View.VISIBLE);
                    divider.setVisibility(View.VISIBLE);
                }
                else {
                    updator.setVisibility(View.GONE);
                    divider.setVisibility(View.GONE);
                }
            }
        });

        manager2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (updator2.getVisibility() == View.GONE) {
                    updator2.setVisibility(View.VISIBLE);
                    divider2.setVisibility(View.VISIBLE);
                    view_btn.setVisibility(View.VISIBLE);
                }
                else {
                    updator2.setVisibility(View.GONE);
                    divider2.setVisibility(View.GONE);
                    view_btn.setVisibility(View.GONE);
                }
            }
        });

        year = (Spinner)findViewById(R.id.year_spinner);
        month = (Spinner)findViewById(R.id.month_spinner);

        ArrayAdapter adapter1 = new ArrayAdapter(settings.this, android.R.layout.simple_spinner_item, months);
        ArrayAdapter adapter2 = new ArrayAdapter(settings.this, android.R.layout.simple_spinner_item, years);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = years[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = months[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        month.setAdapter(adapter1);
        year.setAdapter(adapter2);

        limit = (EditText)findViewById(R.id.limit_settings);
        preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        limit.setText(preferences.getString("user_limit", "2000"));
    }

    public void updateLimit(View view){

        if (Integer.parseInt(limit.getText().toString()) > 0 ){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_limit", limit.getText().toString());
        editor.commit();

            Snackbar snackbar = Snackbar.make(cord, "Limit updated successfuly", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    public void viewPreviousExpensses(View view){

        Intent intent = new Intent(settings.this, expenses.class);
        intent.putExtra("month", selectedMonth + " " + selectedYear);
        startActivity(intent);
    }
}
