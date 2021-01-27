package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardViewAdapter adapter;
    private List<CardElement> cardElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);

        cardElements = new ArrayList<CardElement>();
        adapter = new CardViewAdapter(this, cardElements, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i;
                switch (position){
                    case 0:
                        i = new Intent(MainActivity.this, SchemeAuditActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, HygieneAuditActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(MainActivity.this, SchemeAuditActivity.class);
                        startActivity(i);
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

        prepareAlbums();


        //ImageView of Cover Photo
        try {
            Glide.with(this).load(R.drawable.banner).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.scheme_audit,
                R.drawable.hygen_audit,
                R.drawable.sync_settings,
                R.drawable.project_status};

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

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}