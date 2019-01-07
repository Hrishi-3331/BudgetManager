package com.hrishi_3331.hrishi_studio.expensemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView limit, expense, saving, alert;
    Budget budget;
    AlertDialog.Builder builder;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        limit = (TextView)findViewById(R.id.main_limit);
        expense = (TextView)findViewById(R.id.main_expense);
        saving = (TextView)findViewById(R.id.main_saving);
        alert = (TextView)findViewById(R.id.main_alert);

        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit Application?");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            budget = new Budget(this);
            limit.setText("₹ " + budget.getLimit());
            expense.setText("₹ " + budget.getExpense());
            saving.setText("₹ " + budget.getSaving());

            if (budget.getExpense() > budget.getLimit()) {
                alert.setText(R.string.excedlimit);
                alert.setTextColor(Color.RED);
            } else {
                alert.setText(R.string.withinlimit);
                alert.setTextColor(Color.rgb(50,150,50));
            }
        }
        catch (Exception e){

        }
    }

    public void viewExp(View view){
        Calendar calendar = Calendar.getInstance();
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + calendar.get(Calendar.YEAR);
        Intent intent = new Intent(MainActivity.this, expenses.class);
        intent.putExtra("month", month);
        startActivity(intent);
    }

    public void addExp(View view){
        Intent intent = new Intent(MainActivity.this, addExpense.class);
        startActivity(intent);
    }

    public void settings(View view){
        Intent intent = new Intent(MainActivity.this, settings.class);
        startActivity(intent);
    }

    public void about(View view){
        Intent intent = new Intent(MainActivity.this, about.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        dialog.show();
    }
}
