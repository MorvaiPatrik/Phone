package com.example.phone;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class FirstPageActivity extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseFirestore firestore;
    private CollectionReference collectionReference;

    private AlarmManager manager;
    private RecyclerView recyclerView;
    private AboutAdapter adapter;
    private ArrayList<About> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        user = FirebaseAuth.getInstance().getCurrentUser();

        items = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new AboutAdapter(this, items);
        recyclerView.setAdapter(adapter);

        firestore = FirebaseFirestore.getInstance();
        collectionReference = firestore.collection("Items");
        queryData();
    }


    private void queryData() {
        items.clear();

        collectionReference.orderBy("name").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                About item = documentSnapshot.toObject(About.class);
                items.add(item);
            }

            if (items.isEmpty()) {
                initializeData();
                queryData();
            }
            adapter.notifyDataSetChanged();
        });
    }

    private void initializeData() {
        String[] itemPackName = getResources().getStringArray(R.array.nameOfPack);
        String[] itemPrice = getResources().getStringArray(R.array.priceOfPack);
        String[] itemInfo1 = getResources().getStringArray(R.array.info1OfPack);
        String[] itemInfo2 = getResources().getStringArray(R.array.info2OfPack);
        String[] itemInfo3 = getResources().getStringArray(R.array.info3OfPack);


        for (int i = 0; i < itemPackName.length; i++) {
            collectionReference.add(new About(
                    itemPackName[i],
                    itemPrice[i],
                    itemInfo1[i],
                    itemInfo2[i],
                    itemInfo3[i],
                    0));
        }
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addToCart(About about) {
        about.addToCart();
        Snackbar.make(findViewById(android.R.id.content), "The cart contains " + about.getCount() + " of " + about.getName(), Snackbar.LENGTH_SHORT).show();
    }

    public void delete(About about) {
        about.delete();
        Snackbar.make(findViewById(android.R.id.content), "The cart contains " + about.getCount() + " of " + about.getName(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}