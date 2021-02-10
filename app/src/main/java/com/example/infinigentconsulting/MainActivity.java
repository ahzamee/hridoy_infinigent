package com.example.infinigentconsulting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private RecyclerView recyclerView;
    private CardViewAdapter adapter;
    private List<CardElement> cardElements;
    private boolean IsInternetAvaiable = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internetIsConnected();
        myDb = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recycler_view);

        cardElements = new ArrayList<CardElement>();
        adapter = new CardViewAdapter(this, cardElements, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i;
                switch (position) {
                    case 0:
                        i = new Intent(MainActivity.this, SchemeAuditActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, HygieneAuditActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        if(IsInternetAvaiable== true) {
                            //new GetListofUser().execute();
                           // new GetDistributorList().execute();
//                            new GetAICList().execute();
//                            new GetASMList().execute();
                           // new GetCommentsTypeList().execute();
                            new GetCommentsList().execute();

                        }
                        else{
                            Toast.makeText(MainActivity.this, "Internet Is Not Available,Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3:

                        i = new Intent(MainActivity.this, SchemeAuditActivity.class);
                        startActivity(i);
                        break;
                }
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //myDb.getAllData();
        prepareAlbums();


        //ImageView of Cover Photo
        try {
            Glide.with(this).load(R.drawable.banner).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean internetIsConnected() {


        try {

            String command = "ping -c 1 google.com";
            IsInternetAvaiable = (Runtime.getRuntime().exec(command).waitFor() == 0);
            return IsInternetAvaiable;
        } catch (Exception e) {
            return IsInternetAvaiable;
        }
    }

    // method for showing image and text in the cards
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.scheme_auditt,
                R.drawable.hygiene_audit,
                R.drawable.sync,
                R.drawable.status};

        CardElement a = new CardElement("Scheme Audit", covers[0]);
        cardElements.add(a);

        a = new CardElement("Hygiene Audit", covers[1]);
        cardElements.add(a);

        a = new CardElement("Sync", covers[2]);
        cardElements.add(a);

        a = new CardElement("Status", covers[3]);
        cardElements.add(a);


        adapter.notifyDataSetChanged();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public void AddDistributorList(ArrayList<GenericClass> DistributorList) {

        for (GenericClass item : DistributorList) {
            boolean isInserted = myDb.insertDistributorList(item.Id,item.Name,item.IsActive);

            if (isInserted == true)
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }



    }

    public void AddAICList(ArrayList<GenericClass> AICList) {

        for (GenericClass item : AICList) {
            boolean isInserted = myDb.insertAICList(item.Id,item.Name,item.IsActive);

            if (isInserted == true)
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }



    }

    public void AddASMList(ArrayList<GenericClass> ASMList) {

        for (GenericClass item : ASMList) {
            boolean isInserted = myDb.insertASMList(item.Id,item.Name,item.IsActive);

            if (isInserted == true)
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }



    }

    public void AddCommentsTypeList(ArrayList<CommentsTypeClass> CommentsTypeList) {

        for (CommentsTypeClass item : CommentsTypeList) {
            boolean isInserted = myDb.insertCommentsTypeList(item.Id,item.CommentsType);

            if (isInserted == true)
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }



    }

    public void AddCommentsList(ArrayList<CommnetsClass> CommentsList) {

        for (CommnetsClass item : CommentsList) {
            boolean isInserted = myDb.insertCommentsList(item.Id,item.CommentsTypeId,item.Comments);

            if (isInserted == true)
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }



    }

    public void AddData(ArrayList<UserClass> UserList) {

            for (UserClass item : UserList) {
                boolean isInserted = myDb.insertData(item.Name, item.Email, item.MobileNo, item.Password, item.IsActive);

                if (isInserted == true)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }



    }

    public void viewAll() {

        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Surname :"+ res.getString(2)+"\n");
            buffer.append("Marks :"+ res.getString(3)+"\n\n");


        // Show all data
        showMessage("Data",buffer.toString());
    }
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void DeleteData() {

        Integer deletedRows = myDb.deleteData();
        if (deletedRows > 0)
            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
    }

    /* Begin-- Collect User information data from DB with API --Begin*/
    public class GetListofUser extends AsyncTask<ArrayList<String>, Void, ArrayList<UserClass>> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(ArrayList<UserClass> UserList) {
           // super.onPostExecute(i);
            if (UserList.size() > 1) {
                AddData(UserList);
                viewAll();

            } else {
                 // onLoginFailed();
                Log.d("a","not found");
            }
        }

        protected ArrayList<UserClass> doInBackground(ArrayList<String>... params) {
            Integer result;
            JSONObject jObject;
            JSONArray jsonArray = null;
            int i = 0;
            String str = "http://202.126.122.85:72/api/User";//"http://202.126.122.85:71/api/Division";
            String response = "";
            ArrayList<UserClass> UserArrayList = new ArrayList();
            URL url = null;
            try {
                url = new URL(str);
            } catch (MalformedURLException e) {
                response = e.getMessage();
            } catch (Exception ex) {
                response = ex.getMessage();
            }
            HttpURLConnection conn = null;

            JSONObject jsonObject;
            JSONStringer userJson = null;
            OutputStreamWriter outputStreamWriter = null;
            int responseCode;
            BufferedReader br;
            String line;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //starting
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            try {
                responseCode = conn.getResponseCode();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    line = br.readLine();
                    if (line != null) {
                        response = response + line;
                    }
                    else
                    {
                        break;
                    }

                }
                jObject = null;
                if (!response.isEmpty()) {
                    try {
                        jsonArray = new JSONArray(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {

                    for (i = 0; i < jsonArray.length(); i++) {
                        JSONObject object2 = jsonArray.getJSONObject(i);
                        UserClass _UserClass= new UserClass ();
                        _UserClass.setId(object2.getInt("Id"));
                        _UserClass.setName(object2.getString("Name"));
                        _UserClass.setEmail(object2.getString("Email"));
                      _UserClass.setMobileNo(object2.getString("MobileNo"));
                        _UserClass.setPassword(object2.getString("Password"));
                       _UserClass.setIsActive(object2.getBoolean("IsActive"));

                        UserArrayList.add(_UserClass);

                    }
                } catch (JSONException e322) {
                    response = e322.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return UserArrayList ;
        }
    }
    /*END-- Collect User information data from DB with API --END*/


    /* Begin-- Collect Distributor List information data from DB with API --Begin*/
    public class GetDistributorList extends AsyncTask<ArrayList<String>, Void, ArrayList<GenericClass>> {
           protected void onPreExecute() {
               super.onPreExecute();
           }

           protected void onPostExecute(ArrayList<GenericClass> DistributorList) {
               // super.onPostExecute(i);
               if (DistributorList.size() > 1) {
                   AddDistributorList(DistributorList);


               } else {
                   // onLoginFailed();
               }
           }

           protected ArrayList<GenericClass> doInBackground(ArrayList<String>... params) {
               Integer result;
               JSONObject jObject;
               JSONArray jsonArray = null;
               int i = 0;
               String str = "http://202.126.122.85:72/api/DistributorDetails";//"http://202.126.122.85:71/api/Division";
               String response = "";
               ArrayList<GenericClass> DistributorArrayList = new ArrayList();
               URL url = null;
               try {
                   url = new URL(str);
               } catch (MalformedURLException e) {
                   response = e.getMessage();
               } catch (Exception ex) {
                   response = ex.getMessage();
               }
               HttpURLConnection conn = null;

               JSONObject jsonObject;
               JSONStringer userJson = null;
               OutputStreamWriter outputStreamWriter = null;
               int responseCode;
               BufferedReader br;
               String line;
               try {
                   conn = (HttpURLConnection) url.openConnection();
               } catch (IOException e) {
                   e.printStackTrace();
               }
               //starting
               try {
                   conn.setRequestMethod("GET");
               } catch (ProtocolException e) {
                   e.printStackTrace();
               }

               try {
                   responseCode = conn.getResponseCode();
                   br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                   while (true) {
                       line = br.readLine();
                       if (line != null) {
                           response = response + line;
                       }
                       else
                       {
                           break;
                       }

                   }
                   jObject = null;
                   if (!response.isEmpty()) {
                       try {
                           jsonArray = new JSONArray(response);

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
                   try {

                       for (i = 0; i < jsonArray.length(); i++) {
                           JSONObject object2 = jsonArray.getJSONObject(i);
                           GenericClass _Distributor= new GenericClass ();
                           _Distributor.setId(object2.getInt("Id"));
                           _Distributor.setName(object2.getString("Name"));
                           _Distributor.setIsActive(object2.getBoolean("IsActive"));



                           DistributorArrayList.add(_Distributor);

                       }
                   } catch (JSONException e322) {
                       response = e322.getMessage();
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }


               return DistributorArrayList ;
           }
       }
       /*END-- Collect Distributor List data from DB with API --END*/

    /* Begin-- Collect AIC List information data from DB with API --Begin*/
    public class GetAICList extends AsyncTask<ArrayList<String>, Void, ArrayList<GenericClass>> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(ArrayList<GenericClass> AICList) {
            // super.onPostExecute(i);
            if (AICList.size() > 1) {
                AddAICList(AICList);


            } else {
                // onLoginFailed();
            }
        }

        protected ArrayList<GenericClass> doInBackground(ArrayList<String>... params) {
            Integer result;
            JSONObject jObject;
            JSONArray jsonArray = null;
            int i = 0;
            String str = "http://202.126.122.85:72/api/AIC";//"http://202.126.122.85:71/api/Division";
            String response = "";
            ArrayList<GenericClass> AICArrayList = new ArrayList();
            URL url = null;
            try {
                url = new URL(str);
            } catch (MalformedURLException e) {
                response = e.getMessage();
            } catch (Exception ex) {
                response = ex.getMessage();
            }
            HttpURLConnection conn = null;

            JSONObject jsonObject;
            JSONStringer userJson = null;
            OutputStreamWriter outputStreamWriter = null;
            int responseCode;
            BufferedReader br;
            String line;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //starting
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            try {
                responseCode = conn.getResponseCode();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    line = br.readLine();
                    if (line != null) {
                        response = response + line;
                    }
                    else
                    {
                        break;
                    }

                }
                jObject = null;
                if (!response.isEmpty()) {
                    try {
                        jsonArray = new JSONArray(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {

                    for (i = 0; i < jsonArray.length(); i++) {
                        JSONObject object2 = jsonArray.getJSONObject(i);
                        GenericClass _AIC= new GenericClass ();
                        _AIC.setId(object2.getInt("Id"));
                        _AIC.setName(object2.getString("Name"));
                        _AIC.setIsActive(object2.getBoolean("IsActive"));



                        AICArrayList.add(_AIC);

                    }
                } catch (JSONException e322) {
                    response = e322.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return AICArrayList ;
        }
    }
    /*END-- Collect AIC List data from DB with API --END*/

    /* Begin-- Collect ASM List information data from DB with API --Begin*/
    public class GetASMList extends AsyncTask<ArrayList<String>, Void, ArrayList<GenericClass>> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(ArrayList<GenericClass> ASMList) {
            // super.onPostExecute(i);
            if (ASMList.size() > 1) {
                AddASMList(ASMList);


            } else {
                // onLoginFailed();
            }
        }

        protected ArrayList<GenericClass> doInBackground(ArrayList<String>... params) {
            Integer result;
            JSONObject jObject;
            JSONArray jsonArray = null;
            int i = 0;
            String str = "http://202.126.122.85:72/api/ASM";//"http://202.126.122.85:71/api/Division";
            String response = "";
            ArrayList<GenericClass> ASMArrayList = new ArrayList();
            URL url = null;
            try {
                url = new URL(str);
            } catch (MalformedURLException e) {
                response = e.getMessage();
            } catch (Exception ex) {
                response = ex.getMessage();
            }
            HttpURLConnection conn = null;

            JSONObject jsonObject;
            JSONStringer userJson = null;
            OutputStreamWriter outputStreamWriter = null;
            int responseCode;
            BufferedReader br;
            String line;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //starting
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            try {
                responseCode = conn.getResponseCode();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    line = br.readLine();
                    if (line != null) {
                        response = response + line;
                    }
                    else
                    {
                        break;
                    }

                }
                jObject = null;
                if (!response.isEmpty()) {
                    try {
                        jsonArray = new JSONArray(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {

                    for (i = 0; i < jsonArray.length(); i++) {
                        JSONObject object2 = jsonArray.getJSONObject(i);
                        GenericClass _ASM= new GenericClass ();
                        _ASM.setId(object2.getInt("Id"));
                        _ASM.setName(object2.getString("Name"));
                        _ASM.setIsActive(object2.getBoolean("IsActive"));



                        ASMArrayList.add(_ASM);

                    }
                } catch (JSONException e322) {
                    response = e322.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return ASMArrayList ;
        }
    }
    /*END-- Collect ASM List data from DB with API --END*/

    /* Begin-- Collect CommentsType List information data from DB with API --Begin*/
    public class GetCommentsTypeList extends AsyncTask<ArrayList<String>, Void, ArrayList<CommentsTypeClass>> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(ArrayList<CommentsTypeClass> CommentsTypeList) {
            // super.onPostExecute(i);
            if (CommentsTypeList.size() > 1) {
                AddCommentsTypeList(CommentsTypeList);


            } else {
                // onLoginFailed();
            }
        }

        protected ArrayList<CommentsTypeClass> doInBackground(ArrayList<String>... params) {
            Integer result;
            JSONObject jObject;
            JSONArray jsonArray = null;
            int i = 0;
            String str = "http://202.126.122.85:72/api/CommentsType";//"http://202.126.122.85:71/api/Division";
            String response = "";
            ArrayList<CommentsTypeClass> CommentsTypeArrayList = new ArrayList();
            URL url = null;
            try {
                url = new URL(str);
            } catch (MalformedURLException e) {
                response = e.getMessage();
            } catch (Exception ex) {
                response = ex.getMessage();
            }
            HttpURLConnection conn = null;

            JSONObject jsonObject;
            JSONStringer userJson = null;
            OutputStreamWriter outputStreamWriter = null;
            int responseCode;
            BufferedReader br;
            String line;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //starting
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            try {
                responseCode = conn.getResponseCode();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    line = br.readLine();
                    if (line != null) {
                        response = response + line;
                    }
                    else
                    {
                        break;
                    }

                }
                jObject = null;
                if (!response.isEmpty()) {
                    try {
                        jsonArray = new JSONArray(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {

                    for (i = 0; i < jsonArray.length(); i++) {
                        JSONObject object2 = jsonArray.getJSONObject(i);
                        CommentsTypeClass _CommentsType= new CommentsTypeClass ();
                        _CommentsType.setId(object2.getInt("Id"));
                        _CommentsType.setCommentsType(object2.getString("CommentsType"));



                        CommentsTypeArrayList.add(_CommentsType);

                    }
                } catch (JSONException e322) {
                    response = e322.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return CommentsTypeArrayList ;
        }
    }
    /*END-- Collect CommentsType List data from DB with API --END*/

    /* Begin-- Collect Comments List information data from DB with API --Begin*/
    public class GetCommentsList extends AsyncTask<ArrayList<String>, Void, ArrayList<CommnetsClass>> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(ArrayList<CommnetsClass> CommentsList) {
            // super.onPostExecute(i);
            if (CommentsList.size() > 1) {
                AddCommentsList(CommentsList);


            } else {
                // onLoginFailed();
            }
        }

        protected ArrayList<CommnetsClass> doInBackground(ArrayList<String>... params) {
            Integer result;
            JSONObject jObject;
            JSONArray jsonArray = null;
            int i = 0;
            String str = "http://202.126.122.85:72/api/Commnets";//"http://202.126.122.85:71/api/Division";
            String response = "";
            ArrayList<CommnetsClass> CommentsArrayList = new ArrayList();
            URL url = null;
            try {
                url = new URL(str);
            } catch (MalformedURLException e) {
                response = e.getMessage();
            } catch (Exception ex) {
                response = ex.getMessage();
            }
            HttpURLConnection conn = null;

            JSONObject jsonObject;
            JSONStringer userJson = null;
            OutputStreamWriter outputStreamWriter = null;
            int responseCode;
            BufferedReader br;
            String line;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //starting
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            try {
                responseCode = conn.getResponseCode();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    line = br.readLine();
                    if (line != null) {
                        response = response + line;
                    }
                    else
                    {
                        break;
                    }

                }
                jObject = null;
                if (!response.isEmpty()) {
                    try {
                        jsonArray = new JSONArray(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {

                    for (i = 0; i < jsonArray.length(); i++) {
                        JSONObject object2 = jsonArray.getJSONObject(i);
                        CommnetsClass _Comments= new CommnetsClass ();
                        _Comments.setId(object2.getInt("Id"));
                        _Comments.setCommentsTypeId(object2.getInt("CommentsTypeId"));
                        _Comments.setComments(object2.getString("Comments"));



                        CommentsArrayList.add(_Comments);

                    }
                } catch (JSONException e322) {
                    response = e322.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return CommentsArrayList ;
        }
    }
    /*END-- Collect Comments List data from DB with API --END*/


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private final int spanCount;
        private final int spacing;
        private final boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}