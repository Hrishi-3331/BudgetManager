package com.hrishi_3331.hrishi_studio.expensemanager;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ExpensesGenerator {

    Context context;
    String contents;
    double totalExp = 0;

    public ExpensesGenerator(Context context){

        this.context = context;

        try {
            FileInputStream inputStream = context.openFileInput("ExpenseRegister.txt");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine())!= null){
                builder.append(line);
            }

            contents = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Expense> getExpenses(String cmonth) {

        ArrayList<Expense> expenses = new ArrayList<>();

        String[] parts = contents.split("comhrishi3331expmanager ");
        for (int i = 1; i< parts.length; i++){
            String[] subpart1 = parts[i].split("comtitle ");
            String[] subpart2 = subpart1[1].split("comdate ");
            String title = subpart2[0];
            String[] subpart3 = subpart2[1].split("comdescription ");
            String date = subpart3[0];
            String[] subpart4 = subpart3[1].split("comamount ");
            String description = subpart4[0];
            String[] subpart5 = subpart4[1].split("commonth ");
            String amount = subpart5[0];
            String month = subpart5[1];

            Expense temp = new Expense(context, date, title, amount, month );

            if (cmonth.equals(month)) {
                totalExp = totalExp + Integer.parseInt(amount);
                temp.addDescription(description);
                expenses.add(temp);
            }
        }
        return expenses;
    }

    public double getTotalExp(){
        return totalExp;
    }
}
