package com.hrishi_3331.hrishi_studio.expensemanager;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SnackbarContentLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class addExpense extends AppCompatActivity {

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private EditText date, title, description, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        date = (EditText)findViewById(R.id.date);
        title = (EditText)findViewById(R.id.exptitle);
        description = (EditText)findViewById(R.id.expdesc);
        amount = (EditText)findViewById(R.id.expamount);

        builder = new AlertDialog.Builder(addExpense.this);
        builder.setTitle("Save Expense?");
        builder.setMessage("Do you want to cancel without saving?");
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

        alertDialog = builder.create();

        java.util.Date date1 = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        date.setText(format.format(date1));


    }

    public void cancel(View view){
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        alertDialog.show();
    }

    public void submit(View view){
        String title, date,  desc, amount;

        title = this.title.getText().toString();
        date = this.date.getText().toString();
        desc = this.description.getText().toString();
        amount = this.amount.getText().toString();

        Calendar calendar = Calendar.getInstance();
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + calendar.get(Calendar.YEAR);

        if (!title.isEmpty() && !date.isEmpty() && !amount.isEmpty()){

            Expense expense = new Expense(getApplicationContext(), date, title, amount, month);
            if (!desc.isEmpty()){
                expense.addDescription(desc);
            }
            else {
                expense.addDescription("null");
            }
            expense.saveExpense();
            finish();

        }
        else {
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        }

    }
}
