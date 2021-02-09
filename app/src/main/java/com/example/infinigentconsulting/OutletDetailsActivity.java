package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class OutletDetailsActivity extends AppCompatActivity {
    private ImageButton _outlate_previous_button;
    private AutoCompleteTextView _distributor_name_text_view, _aic_name_text_view, _asm_name_text_view;
    DatabaseHelper myDb;
    private ArrayList<GenericClass> DistributorDetailsList, AICNameList, ASMNameList;
    private Cursor distributor_db_cursor, aic_db_cursor, asm_db_cursor;
    private GenericClassArrayAdapter distributorArrayAdapter,aicArrayAdapter,asmArrayAdapter;
    String _id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);
        distributor_db_cursor = myDb.getDistributorList();
        aic_db_cursor = myDb.getAICNameList();
        asm_db_cursor = myDb.getASMNameList();


        DistributorDetailsList = new ArrayList<GenericClass>();
        AICNameList = new ArrayList<GenericClass>();
        ASMNameList = new ArrayList<GenericClass>();
        // find view by ids
        _distributor_name_text_view = findViewById(R.id.distributor_name);
        _aic_name_text_view = findViewById(R.id.aic_name);
        _asm_name_text_view = findViewById(R.id.asm_name);



        initCollapsingToolbar();

        // change keyboard focus

        findViewById(R.id.outlet_details_cl).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });




        // Db to Arraylist<GenericClass> to StringArray
        getGenericDetailsAsArrayList(DistributorDetailsList, distributor_db_cursor);
        //Db to Arraylist<GenericClass> to StringArray
        getGenericDetailsAsArrayList(AICNameList, aic_db_cursor);
        //Db to Arraylist<GenericClass> to StringArray
        getGenericDetailsAsArrayList(ASMNameList, asm_db_cursor);

         // Distributor Name Array Adapter

        distributorArrayAdapter = new GenericClassArrayAdapter(this, R.layout.spinner_layout, DistributorDetailsList);
        _distributor_name_text_view.setThreshold(1);
        _distributor_name_text_view.setAdapter(distributorArrayAdapter);

        //Distributor Name TEXT VIEW ON CLICK LISTENER

        _distributor_name_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                _distributor_name_text_view.showDropDown();
            }
        });
        // handle click event and set on textview
        _distributor_name_text_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GenericClass genericDetails = (GenericClass) adapterView.getItemAtPosition(i);
                _distributor_name_text_view.setText(genericDetails.getName());
                 _id = String.valueOf(genericDetails.getId());
            }
        });



        //AIC Dropdown
        // AIC Name Array Adapter
        aicArrayAdapter = new GenericClassArrayAdapter(this, R.layout.spinner_layout, AICNameList);
        _aic_name_text_view.setThreshold(1);
        _aic_name_text_view.setAdapter(aicArrayAdapter);

        //AIC Name TEXT VIEW ON CLICK LISTENER

        _aic_name_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                _aic_name_text_view.showDropDown();
            }
        });
        // handle click event and set desc on textview
        _aic_name_text_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GenericClass genericDetails = (GenericClass) adapterView.getItemAtPosition(i);
                _aic_name_text_view.setText(genericDetails.getName());
            }
        });

        //ASM Dropdown
        // ASM Name Array Adapter
        asmArrayAdapter = new GenericClassArrayAdapter(this, R.layout.spinner_layout, ASMNameList);
        _asm_name_text_view.setThreshold(1);
        _asm_name_text_view.setAdapter(asmArrayAdapter);

        //ASM Name TEXT VIEW ON CLICK LISTENER

        _asm_name_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                _asm_name_text_view.showDropDown();
            }
        });
        // handle click event and set desc on textview
        _asm_name_text_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GenericClass genericDetails = (GenericClass) adapterView.getItemAtPosition(i);
                _asm_name_text_view.setText(genericDetails.getName());
            }
        });


        _outlate_previous_button = findViewById(R.id.outlate_previous_button);
        _outlate_previous_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OutletDetailsActivity.this, SchemeDetailsActivity.class);
                startActivity(i);
            }
        });
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

    public ArrayList<GenericClass> getGenericDetailsAsArrayList(ArrayList<GenericClass> genericClassArrayList, Cursor cursor) {

        Cursor res = Objects.requireNonNull(cursor);
        if (res.getCount() == 0) {
            // show message
            // showMessage("Error","Nothing found");
            Toast.makeText(OutletDetailsActivity.this, "Not Found", Toast.LENGTH_LONG).show();

        }
        while (res.moveToNext()) {
            GenericClass _genericClassDetails = new GenericClass();
            _genericClassDetails.setId(res.getInt(0));
            _genericClassDetails.setName(res.getString(1));
            _genericClassDetails.setIsActive(res.getInt(2) > 0);
            genericClassArrayList.add(_genericClassDetails);


        }
        return genericClassArrayList;
    }



}