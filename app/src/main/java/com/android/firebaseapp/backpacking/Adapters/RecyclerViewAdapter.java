package com.android.firebaseapp.backpacking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.firebaseapp.backpacking.Model.Expenses;
import com.android.firebaseapp.backpacking.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    private ArrayList<Expenses> expensesArrayList;
    private OnitemClickListener mListener;



    public interface OnitemClickListener {
        void onItemClick(int position);


    }

    public void setOnItemClickListener(OnitemClickListener listener) {

        mListener = listener;
    }

    public RecyclerViewAdapter(Context context, ArrayList<Expenses> expensesArrayList) {
        this.context = context;
        this.expensesArrayList = expensesArrayList;


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descTextView;
        TextView valueTextView;
        TextView currencyTextView;
        TextView dateTextView;



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            descTextView = itemView.findViewById(R.id.TextViewDescCV);
            valueTextView = itemView.findViewById(R.id.TextViewValueCV);
            currencyTextView = itemView.findViewById(R.id.TextViewCurrency);
            dateTextView = itemView.findViewById(R.id.textViewDate);



        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup,
                false);


        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        Expenses expenses = expensesArrayList.get(i);
        String description = expenses.getDescription();
        Double value = expenses.getValue();
        String currency = expenses.getCurrency();
        String date = expenses.getDate();


        viewHolder.descTextView.setText(description);
        viewHolder.valueTextView.setText(String.valueOf(value ));
        viewHolder.currencyTextView.setText(currency);
        viewHolder.dateTextView.setText(date);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position);
                    }


                }


            }
        });




    }


    @Override
    public int getItemCount() {
        if (expensesArrayList == null) {
            return 0;
        } else {
            return expensesArrayList.size();
        }
    }
    @Override
    public long getItemId(int position) {
        return expensesArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return expensesArrayList.size();
    }


}
