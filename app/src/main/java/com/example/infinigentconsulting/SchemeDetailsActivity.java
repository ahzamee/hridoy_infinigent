package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class SchemeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button _scheme_date_picker, _latest_chalan_date_picker;
    private EditText _scheme_date, _latest_chalan_date;
    private int mYear, mMonth, mDay;
    private ImageButton _scheme_details_previous_button, _scheme_details_next_button;
    private AutoCompleteTextView _comments_type_text_view, _comments_text_view ;
    DatabaseHelper myDb;
    private ArrayList<CommentsTypeClass> CommentsTypeList;
    private ArrayList<CommnetsClass> CommentsList;
    private Cursor comments_type_db_cursor,comments_db_cursor;
    private CommentsTypeAdapter customAdapter;
    private CommentsAdapter commentsAdapter;

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

        myDb = new DatabaseHelper(this);
        comments_type_db_cursor= myDb.getCommentsTypeList();
        CommentsTypeList= new ArrayList<CommentsTypeClass>();
        CommentsList = new ArrayList<>();

        //Db to Arraylist<GenericClass> to StringArray
        getCommentsTypeAsArrayList(CommentsTypeList,comments_type_db_cursor);


        _scheme_details_previous_button = findViewById(R.id.scheme_details_previous_button);
        _scheme_details_next_button = findViewById(R.id.scheme_details_next_button);
        _scheme_date_picker = findViewById(R.id.date_of_scheme_date_picker);
        _latest_chalan_date_picker = findViewById(R.id.latest_chalan_date_picker);
        _comments_type_text_view = findViewById(R.id.comments_type);
        _comments_text_view =findViewById(R.id.comments);

        _scheme_date = findViewById(R.id.date_of_scheme_);
        _latest_chalan_date = findViewById(R.id.latest_chalan_date);

        _scheme_details_previous_button.setOnClickListener(this);
        _scheme_details_next_button.setOnClickListener(this);

        _scheme_date_picker.setOnClickListener(this);
        _latest_chalan_date_picker.setOnClickListener(this);



        // CommentsType Adapter
        customAdapter = new CommentsTypeAdapter(this, R.layout.spinner_layout, CommentsTypeList);
        _comments_type_text_view.setThreshold(1);
        _comments_type_text_view.setAdapter(customAdapter);

        //Comments Type TEXT VIEW ON CLICK LISTENER

        _comments_type_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                _comments_type_text_view.showDropDown();
            }
        });
        // handle click event and set desc on textview
        _comments_type_text_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CommentsTypeClass commentType = (CommentsTypeClass) adapterView.getItemAtPosition(i);
                _comments_type_text_view.setText(commentType.getCommentsType());
                int _id =commentType.getId();
               comments_db_cursor = myDb.getCommentList(_id);
               getCommentsAsArrayList(CommentsList,comments_db_cursor);
            }
        });

        //------------------------------------------------------------------------------------//

        // Comments Adapter
        commentsAdapter= new CommentsAdapter(this, R.layout.spinner_layout, CommentsList);
        _comments_text_view.setThreshold(1);
        _comments_text_view.setAdapter(commentsAdapter);

        //Comments Type TEXT VIEW ON CLICK LISTENER

        _comments_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                _comments_text_view.showDropDown();
            }
        });
        // handle click event and set comment on textview
        _comments_text_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CommnetsClass comment = (CommnetsClass) adapterView.getItemAtPosition(i);
                _comments_text_view.setText(comment.getComments());
            }
        });


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


    public ArrayList<CommentsTypeClass> getCommentsTypeAsArrayList(ArrayList<CommentsTypeClass> commentsTypeArrayList, Cursor cursor) {

        Cursor res = cursor;
        if (res.getCount() == 0) {
            // show message
            // showMessage("Error","Nothing found");
            Toast.makeText(SchemeDetailsActivity.this, "Not Found", Toast.LENGTH_LONG).show();
//            return DistributordetailsList;
        }
        while (res.moveToNext()) {
            CommentsTypeClass _commentsTypeDetails = new CommentsTypeClass();
            _commentsTypeDetails.setId(res.getInt(0));
            _commentsTypeDetails.setCommentsType(res.getString(1));
            commentsTypeArrayList.add(_commentsTypeDetails);



        }
        return commentsTypeArrayList;
    }


    public ArrayList<CommnetsClass> getCommentsAsArrayList(ArrayList<CommnetsClass> commentsArrayList, Cursor cursor) {

        Cursor res = cursor;
        if (res.getCount() == 0) {
            // show message
            // showMessage("Error","Nothing found");
            Toast.makeText(SchemeDetailsActivity.this, "Not Found", Toast.LENGTH_LONG).show();
//            return DistributordetailsList;
        }
        while (res.moveToNext()) {
            CommnetsClass _comments = new CommnetsClass();
            _comments.setId(res.getInt(0));
            _comments.setCommentsTypeId(res.getInt(1));
            _comments.setComments(res.getString(2));
            commentsArrayList.add(_comments);



        }
        return commentsArrayList;
    }



}