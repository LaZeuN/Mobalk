package com.example.androidmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MediaListActivity extends AppCompatActivity {
    private static final String LOG_TAG = MediaListActivity.class.getName();
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;
    private ArrayList<MediaContent> mContentList;
    private MediaContentAdapter mAdapter;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;



    private int gridNumber = 1;
    private boolean viewRow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        mAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d(LOG_TAG, "Authenticated user!");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mContentList = new ArrayList<>();

        mAdapter = new MediaContentAdapter(this, mContentList);

        mRecyclerView.setAdapter(mAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Contents");

        queryData();


    }

    private void queryData() {
        mContentList.clear();

        mItems.orderBy("identifier").limit(10).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                MediaContent content = document.toObject(MediaContent.class);
                content.setIdentifier(document.getId());
                mContentList.add(content);
            }

            if (mContentList.size() == 0) {
                initializeData();
                queryData();
           }

            mAdapter.notifyDataSetChanged();
        });
    }

    public void deleteContent(MediaContent content) {
        DocumentReference ref = mItems.document(content._getIdentifier());

        ref.delete().addOnSuccessListener(success -> {
            Log.d(LOG_TAG, "Content successfully deleted: " + content._getIdentifier());
        }).addOnFailureListener(failure -> {
            Toast.makeText(this, "Content" + content._getIdentifier() + "cannot be deleted.", Toast.LENGTH_LONG).show();
        });

        queryData();
    }

    public void updateContent(MediaContent content) {

    }

    private void initializeData() {
        String[] identifier = getResources().getStringArray(R.array.content_identifier);
        String[] basedOn = getResources().getStringArray(R.array.content_basedOn);
        String[] status = getResources().getStringArray(R.array.content_status);
        String[] type = getResources().getStringArray(R.array.content_type);
        String[] modality = getResources().getStringArray(R.array.content_modality);
        String[] view = getResources().getStringArray(R.array.content_view);
        String[] subject = getResources().getStringArray(R.array.content_subject);
        String[] encounter = getResources().getStringArray(R.array.content_encounter);
        String[] createdDateTime = getResources().getStringArray(R.array.content_createdDateTime);
        String[] createdPeriod = getResources().getStringArray(R.array.content_createdPeriod);
        String[] issued = getResources().getStringArray(R.array.content_issued);
        String[] operator = getResources().getStringArray(R.array.content_operator);
        String[] reasonCode = getResources().getStringArray(R.array.content_reasonCode);
        String[] bodySite = getResources().getStringArray(R.array.content_bodySite);
        String[] deviceName = getResources().getStringArray(R.array.content_deviceName);
        String[] device = getResources().getStringArray(R.array.content_device);
        String[] height = getResources().getStringArray(R.array.content_height);
        String[] width = getResources().getStringArray(R.array.content_width);
        String[] frames = getResources().getStringArray(R.array.content_frames);
        String[] duration = getResources().getStringArray(R.array.content_duration);
        String[] note = getResources().getStringArray(R.array.content_note);

        TypedArray content = getResources().obtainTypedArray(R.array.content_image);


        for (int i = 0; i < identifier.length; i++) {
            mItems.add(new MediaContent(
                    identifier[i],
                    basedOn[i],
                    status[i],
                    type[i],
                    modality[i],
                    view[i],
                    subject[i],
                    encounter[i],
                    createdDateTime[i],
                    createdPeriod[i],
                    issued[i],
                    operator[i],
                    reasonCode[i],
                    bodySite[i],
                    deviceName[i],
                    device[i],
                    height[i],
                    width[i],
                    frames[i],
                    duration[i],
                    content.getResourceId(i, 0),
                    note[i]
            ));
        }

        content.recycle();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.media_list_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(LOG_TAG, s);
                mAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.log_out_button:
                Log.d(LOG_TAG, "Log out clicked");
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.setting_button:
                Log.d(LOG_TAG, "Settings clicked");
                return true;
            case R.id.add_content:
                Intent intent = new Intent(this, UploadActivity.class);
                startActivity(intent);
                Log.d(LOG_TAG, "Add content clicked");
                return true;
            case R.id.view_selector:
                Log.d(LOG_TAG, "View selector clicked");
                if (viewRow) {
                    changeSpanCount(item, R.drawable.ic_view_grid, 1);
                } else {
                    changeSpanCount(item, R.drawable.ic_view_row, 2);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void changeSpanCount(MenuItem item, int drawableId, int spanCount) {
        viewRow = !viewRow;
        item.setIcon(drawableId);
        GridLayoutManager layoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
        layoutManager.setSpanCount(spanCount);
    }

}