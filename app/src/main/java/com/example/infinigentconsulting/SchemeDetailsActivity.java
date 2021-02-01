package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Calendar;

public class SchemeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button _scheme_date_picker, _latest_chalan_date_picker;
    private EditText _scheme_date, _latest_chalan_date;
    private int mYear, mMonth, mDay;
    private ImageButton _scheme_details_previous_button, _scheme_details_next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        // change keyboard focus

        findViewById(R.id.scheme_details_cl).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });


        _scheme_details_previous_button = findViewById(R.id.scheme_details_previous_button);
        _scheme_details_next_button = findViewById(R.id.scheme_details_next_button);
        _scheme_date_picker = (Button) findViewById(R.id.date_of_scheme_date_picker);
        _latest_chalan_date_picker = (Button) findViewById(R.id.latest_chalan_date_picker);

        _scheme_date = (EditText) findViewById(R.id.date_of_scheme_);
        _latest_chalan_date = (EditText) findViewById(R.id.latest_chalan_date);

        _scheme_details_previous_button.setOnClickListener(this);
        _scheme_details_next_button.setOnClickListener(this);

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
        if (view == _scheme_details_previous_button) {
            Intent i = new Intent(SchemeDetailsActivity.this, OutletDetailsActivity.class);
            startActivity(i);
        }
        if (view == _scheme_details_next_button) {
            Intent i = new Intent(SchemeDetailsActivity.this, ImageBrowsingActivity.class);
            startActivity(i);
        }
        if (view == _scheme_date_picker) {
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
        if (view == _latest_chalan_date_picker) {
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


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

}