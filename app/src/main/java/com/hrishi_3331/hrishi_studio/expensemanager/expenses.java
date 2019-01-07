package com.hrishi_3331.hrishi_studio.expensemanager;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class expenses extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Expense> expenses;
    private ExpenseAdapter adapter;
    String cmonth;
    LinearLayout noalert;
    TextView month_name, totalexp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        Intent intent = getIntent();
        cmonth = intent.getStringExtra("month");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("My Expenses");

        recyclerView = (RecyclerView)findViewById(R.id.expenseView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        noalert = (LinearLayout)findViewById(R.id.noalert);
        month_name = (TextView)findViewById(R.id.month_name);
        month_name.setText(cmonth);

        totalexp = (TextView)findViewById(R.id.total_expenditure);


    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            ExpensesGenerator generator = new ExpensesGenerator(getApplicationContext());
            expenses = generator.getExpenses(cmonth);
            Collections.reverse(expenses);
            totalexp.setText("₹ " + generator.getTotalExp());
        } catch (Exception e){
            e.printStackTrace();
        }
        adapter = new ExpenseAdapter(expenses);
        recyclerView.setAdapter(adapter);
        if (adapter.getItemCount() > 0){
            noalert.setVisibility(View.GONE);
        }
    }
}
