package com.hrishi_3331.hrishi_studio.expensemanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ExpenseViewHolder extends RecyclerView.ViewHolder {

    TextView title, description, date, amount;

    public ExpenseViewHolder(@NonNull View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.card_title);
        description = (TextView)itemView.findViewById(R.id.card_description);
        date = (TextView)itemView.findViewById(R.id.card_date);
        amount = (TextView)itemView.findViewById(R.id.card_amount);
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setDate(String date){
        this.date.setText(date);
    }

    public void setDescription(String description){
        if(!description.equals("null")) {
            this.description.setText(description);
        }
        else {
            this.description.setText(" ");
        }
    }

    public void setAmount(String amount){
        this.amount.setText(amount);
    }


}
