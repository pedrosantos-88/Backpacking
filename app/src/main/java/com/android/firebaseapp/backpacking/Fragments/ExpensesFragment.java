package com.android.firebaseapp.backpacking.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.firebaseapp.backpacking.Adapters.RecyclerViewAdapter;
import com.android.firebaseapp.backpacking.Adapters.RecyclerViewAdapter.OnitemClickListener;
import com.android.firebaseapp.backpacking.Model.Expenses;
import com.android.firebaseapp.backpacking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ExpensesFragment extends Fragment {

    private DatabaseReference databaseReference;
    private Button mAddButton;
    private EditText mEditTextDesc, mEditTextValue;
    private TextView mTextViewTotal;
    private TextView mTextViewBudget;
    private TextView mTextViewCoinExp;
    private TextView mTextViewBudgetCoin;
    private TextView mTextViewCurrency;
    private ProgressBar mProgressBar;
    private Calendar calendar;
    private Expenses expenses;
    private LinearLayoutManager mLayoutManager;
    private ValueEventListener valueEventListenerData;
    private ValueEventListener valueEventListenerBudget;
    private ValueEventListener valueEventListenerCurrency;
    private ArrayList<Expenses> expensesArrayList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private ImageView mEditBudgetImageView;
    private Spinner spinner;

    private FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();


    public ExpensesFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();


        databaseReference.child("Products").child(currentFirebaseUser.getUid()).addValueEventListener(valueEventListenerData);
        databaseReference.child("UserCurrency").child(currentFirebaseUser.getUid()).addValueEventListener(valueEventListenerCurrency);
        databaseReference.child("Budget").child(currentFirebaseUser.getUid()).addValueEventListener(valueEventListenerBudget);


    }

    @Override
    public void onStop() {
        super.onStop();
        databaseReference.removeEventListener(valueEventListenerData);
        databaseReference.removeEventListener(valueEventListenerBudget);
        databaseReference.removeEventListener(valueEventListenerCurrency);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);

        mEditTextDesc = rootView.findViewById(R.id.EditTextDesc);
        mEditTextValue = rootView.findViewById(R.id.EditTextValue);
        mTextViewCoinExp = rootView.findViewById(R.id.coinExpTextView);
        mTextViewBudgetCoin = rootView.findViewById(R.id.coinBudgetTextView);
        mTextViewBudget = rootView.findViewById(R.id.budgetTextView);
        mTextViewCurrency = rootView.findViewById(R.id.TextViewCurrency);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mProgressBar = rootView.findViewById(R.id.progressBar);
        expensesArrayList = new ArrayList<>();
        mTextViewTotal = rootView.findViewById(R.id.totalExpTextView);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new RecyclerViewAdapter(getActivity(), expensesArrayList);
        recyclerView.setAdapter(adapter);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mEditBudgetImageView = rootView.findViewById(R.id.editBudgetImageView);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        spinner = rootView.findViewById(R.id.spinner);
        expenses = new Expenses();


        createSpinner();

        calendar = Calendar.getInstance();
        final String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        mAddButton = rootView.findViewById(R.id.BtnAdd);
        mAddButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String descripsion = mEditTextDesc.getText().toString();

                if (descripsion.isEmpty()) {
                    Toast.makeText(getContext(), "Please complete data!", Toast.LENGTH_SHORT).show();

                } else {

                    try {
                        double value = Double.parseDouble(mEditTextValue.getText().toString());
                        expenses.setDate(currentDate);
                        expenses.setDescription(descripsion);
                        expenses.setValue(value);
                        saveExpenses(expenses);

                        mEditTextValue.setText("");
                        mEditTextDesc.setText("");
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Please complete data!", Toast.LENGTH_SHORT).show();
                    }

                }


            }


        });

        valueEventListenerData = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                expensesArrayList.clear();

                double totalAmount = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Expenses expenses = data.getValue(Expenses.class);

                    expensesArrayList.add(expenses);
                    totalAmount += expenses.getValue();
                }
                mTextViewTotal.setText(("Expenses: " + totalAmount));
                mProgressBar.setVisibility(View.INVISIBLE);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        valueEventListenerBudget = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {

                    String budgetVaue = dataSnapshot.child("budgetValue").getValue().toString();

                    mTextViewBudget.setText("Budget : " + budgetVaue);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        valueEventListenerCurrency = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {

                    String selectedCurrency = dataSnapshot.child("selectedCurrency").getValue().toString();


                    mTextViewCoinExp.setText(selectedCurrency);
                    mTextViewBudgetCoin.setText(selectedCurrency);
                    expenses.setCurrency(selectedCurrency);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        databaseReference.child("Products").child(currentFirebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {


                adapter.notifyDataSetChanged();

                try {
                    adapter.setOnItemClickListener(new OnitemClickListener() {


                        @Override
                        public void onItemClick(final int position) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
                            builder.setTitle("Edit/Delete");
                            LinearLayout layout = new LinearLayout(getContext());
                            layout.setOrientation(LinearLayout.VERTICAL);


                            final EditText editDesc = new EditText(getContext());
                            editDesc.setInputType(InputType.TYPE_CLASS_TEXT);
                            editDesc.setHint("New Description");
                            layout.addView(editDesc);
                            final EditText editValue = new EditText(getContext());
                            editValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                            editValue.setHint("New Value");
                            layout.addView(editValue);
                            builder.setView(layout);

                            builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int counter = 0;
                                    String dataKeys = "";
                                    for (DataSnapshot child : dataSnapshot.getChildren()) {

                                        if (counter == position) {
                                            dataKeys = child.getKey();

                                            break;
                                        }
                                        counter++;
                                    }


                                    String newDesc = editDesc.getText().toString().trim();
                                    String newValue = editValue.getText().toString().trim();

                                    if (newDesc.isEmpty() || newValue.isEmpty()) {
                                        Toast.makeText(getActivity(), "Please insert full data", Toast.LENGTH_SHORT).show();
                                    } else {
                                        expenses.setDescription(newDesc);
                                        expenses.setValue(Double.valueOf(newValue));
                                        databaseReference.child("Products").child(currentFirebaseUser.getUid()).child(dataKeys).setValue(expenses);
                                    }
                                }
                            });
                            builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int counter = 0;
                                    String dataKeys = "";
                                    for (DataSnapshot child : dataSnapshot.getChildren()) {

                                        if (counter == position) {
                                            dataKeys = child.getKey();

                                            break;
                                        }
                                        counter++;
                                    }
                                    databaseReference.child("Products").child(currentFirebaseUser.getUid()).child(dataKeys).removeValue();
                                    dialog.cancel();
                                }
                            });
                            builder.show();


                        }


                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        InsertBudget();
        return rootView;
    }

    private Boolean saveExpenses(Expenses expenses) {


        try {

            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Products").child(currentFirebaseUser.getUid()).push().setValue(expenses);


            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public void InsertBudget() {

        mEditBudgetImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                DialogBudget("Budget Edit!");
            }
        });


    }

    public void DialogBudget(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
        builder.setTitle(msg);


        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);


        builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Editable valueBudget = input.getText();

                try {
                    if (valueBudget != null) {


                        final Double budgetValue = Double.valueOf(valueBudget.toString());
                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        databaseReference.child("Budget").child(currentFirebaseUser.getUid()).child("budgetValue").setValue(budgetValue);

                    } else {
                        Toast.makeText(getActivity(), "Please insert Budget ", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


    }

    public void createSpinner() {

        final List<String> coins = new ArrayList<String>();
        coins.add("€");
        coins.add("£");
        coins.add("$");
        coins.add("¥");

        final ArrayAdapter<String> adapterCoins = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, coins);
        adapterCoins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCoins);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String currency = parent.getSelectedItem().toString();

                databaseReference.child("UserCurrency").child(currentFirebaseUser.getUid()).child("selectedCurrency").setValue(currency);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}





