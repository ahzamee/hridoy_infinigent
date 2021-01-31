package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class SchemeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button _scheme_date_picker, _latest_chalan_date_picker;
    private EditText _scheme_date, _latest_chalan_date;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_details);

        _scheme_date_picker =(Button)findViewById(R.id.date_of_scheme_date_picker);
        _latest_chalan_date_picker = (Button)findViewById(R.id.latest_chalan_date_picker);

        _scheme_date =(EditText)findViewById(R.id.date_of_scheme_);
        _latest_chalan_date =(EditText)findViewById(R.id.latest_chalan_date);


        _scheme_date_picker.setOnClickListener(this);
        _latest_chalan_date_picker.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        if(view ==_scheme_date_picker){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            _scheme_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if(view == _latest_chalan_date_picker){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            _latest_chalan_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }



    }
}