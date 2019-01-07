package com.hrishi_3331.hrishi_studio.expensemanager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Locale;

public class Budget {

    double limit, expense, saving;
    Context context;

    public Budget(Context context){
        this.context = context;
        this.limit = 0;
        this.expense = 0;
        this.saving = 0;

        Calendar calendar = Calendar.getInstance();
        String cmonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + calendar.get(Calendar.YEAR);
        ExpensesGenerator generator = new ExpensesGenerator(context);
        generator.getExpenses(cmonth);
        expense = generator.getTotalExp();

        SharedPreferences preferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String temp = preferences.getString("user_limit", "2000");
        limit = Integer.parseInt(temp);

        saving = limit - expense;
    }

    public double getExpense() {
        return expense;
    }

    public double getLimit() {
        return limit;
    }

    public double getSaving() {
        return saving;
    }
}
