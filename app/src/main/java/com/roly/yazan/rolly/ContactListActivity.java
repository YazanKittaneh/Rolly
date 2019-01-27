package com.roly.yazan.rolly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.roly.yazan.rolly.Models.ContactHolder;
import com.roly.yazan.rolly.Models.ContactModel;

import java.util.List;

import javax.annotation.Nullable;

public class ContactListActivity extends AppCompatActivity {

    FirestoreRecyclerAdapter adapter;
    private static final String TAG = ContactListActivity.class.getSimpleName();
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist);
        recyclerView = findViewById(R.id.recView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        //FirebaseApp.initializeApp(this);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        Query query = firestore.collection("users");

        DocumentReference docRef = firestore.collection("users").document("users");



        FirestoreRecyclerOptions<ContactModel> options = new FirestoreRecyclerOptions.Builder<ContactModel>()
                        .setQuery(query, ContactModel.class)
                        .build();

        Log.d(TAG, "After Options created: "+options.toString());

        adapter = new FirestoreRecyclerAdapter<ContactModel, ContactHolder>(options) {
            @Override
            public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.contact for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.contact, parent, false);

                return new ContactHolder(view);
            }

            @Override
            protected void onBindViewHolder(ContactHolder holder, int position, ContactModel model) {
                Log.d(TAG, "onBindViewHolder: "+model.getName());
                holder.bindView(model);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e(TAG + "error", e.getMessage());
            }
        };

        RecyclerView recyclerView = findViewById(R.id.recView);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
