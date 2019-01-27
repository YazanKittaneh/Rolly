package com.roly.yazan.rolly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final EditText nameText = findViewById(R.id.Name);
        final EditText companyText = findViewById(R.id.Company);
        final SeekBar usefullnessBar = findViewById(R.id.usefullness_bar);
        final EditText notesText = findViewById(R.id.notes);
        Button submitButton = findViewById(R.id.submit);
        Button listButton = findViewById(R.id.list);
        FirebaseApp.initializeApp(this);
        //Button contactListButton = findViewById(R.id.contactListFAB);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("name", nameText.getText().toString());
                user.put("company", companyText.getText().toString());
                user.put("usefullness", usefullnessBar.getProgress());
                user.put("notes", notesText.getText().toString());
                saveToDb(user);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
                startActivity(intent);

            }
        });

        /*
        contactListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
                EditText editText = (EditText) findViewById(R.id.notes);
                String message = editText.getText().toString();
                intent.putExtra("We getting an activity ", message);
                startActivity(intent);

            }
        });
           */
    }


    private void saveToDb(Map<String, Object> map){
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        db.collection("users")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
