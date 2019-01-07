package com.hrishi_3331.hrishi_studio.expensemanager;

import android.content.Context;
import java.io.FileOutputStream;

public class Expense {

    Context context;
    String title, amount, description, date, month;
    boolean isDeleted = false;


    public Expense(Context context, String date, String title, String amount,String month){

        this.context = context;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.month = month;
    }

    public void addDescription(String description){
        this.description = description;
    }

    public void saveExpense(){
        try {
            FileOutputStream stream = context.openFileOutput("ExpenseRegister.txt", Context.MODE_APPEND);
            String regy = "comhrishi3331expmanager comtitle "+ title + "comdate " + date + "comdescription " + description + "comamount " + amount + "commonth " + month;
            stream.write(regy.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public void deleteExpense(){
        isDeleted = true;
    }
}
