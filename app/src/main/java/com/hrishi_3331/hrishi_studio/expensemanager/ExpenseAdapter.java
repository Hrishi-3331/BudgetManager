package com.hrishi_3331.hrishi_studio.expensemanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {

    ArrayList<Expense> expenses;

    public ExpenseAdapter(ArrayList<Expense> expenses){
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_card, viewGroup, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder expenseViewHolder, int i) {

        Expense ref = expenses.get(i);
        expenseViewHolder.setAmount( "₹ " +ref.getAmount());
        expenseViewHolder.setDate(ref.getDate());
        expenseViewHolder.setDescription(ref.getDescription());
        expenseViewHolder.setTitle(ref.getTitle());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }
}
