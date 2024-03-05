package com.example.test3.chat;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.test3.R;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ChatActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView tvName;
    EditText edtText;
    ImageButton btnSend;
    private RecyclerView rcChat;
    private ArrayList<ChatDemo> list;
    private ChatAdapter adapter;
    private FirebaseFirestore db;
    private String tokenId, senderUid;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        imgBack = findViewById(R.id.imgBack);
        tvName = findViewById(R.id.tvName);
        edtText = findViewById(R.id.edtChat);
        rcChat = findViewById(R.id.rcChat);
        btnSend = findViewById(R.id.btnSend);

        imgBack.setOnClickListener(v-> finish());

        db = FirebaseFirestore.getInstance();

        getTokenId();

        list = new ArrayList<>();
        rcChat.setHasFixedSize(true);

        rcChat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new ChatAdapter(list);
        rcChat.setAdapter(adapter);
        
        btnSend.setOnClickListener(v-> addDataToFireStore());
    }

    private void getTokenId(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");

        senderUid = getIntent().getStringExtra("uid");
        tvName.setText(getIntent().getStringExtra("name"));

        ref.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).child("Chat").child(senderUid).child("ChatToken").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tokenId = snapshot.getValue(String.class);
                if (!TextUtils.isEmpty(tokenId)) {

                    loadRecyclerViewData();
                    setupSendButton();
                } else {
                    tokenId = usingRandomUUID();
                    String mobile = getIntent().getStringExtra("mobile");

                    DatabaseReference mRef = database.getReference("Users").child(mAuth.getCurrentUser().getUid()).child("Chat").child(senderUid);
                    mRef.child("ChatToken").setValue(tokenId);
                    mRef.child("Mobile").setValue(mobile);
                    mRef.child("Name").setValue(tvName.getText().toString());
                    mRef.child("Uid").setValue(senderUid);

                    mRef = database.getReference("Users").child(senderUid).child("Chat").child(mAuth.getCurrentUser().getUid());
                    mRef.child("ChatToken").setValue(tokenId);
                    mRef.child("Mobile").setValue(mAuth.getCurrentUser().getPhoneNumber());
                    mRef.child("Uid").setValue(mAuth.getCurrentUser().getUid());

                    SharedPreferences sharedpreferences = getSharedPreferences("mypreference", Context.MODE_PRIVATE);
                    if (sharedpreferences.contains("Name")) {
                        String userName = sharedpreferences.getString("Name", "");
                        mRef.child("Name").setValue(userName);
                    }

                    fireStorePath();
                }
            }

            private void fireStorePath() {
                loadRecyclerViewData();
                setupSendButton();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to get TokenId: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSendButton() {
        btnSend.setOnClickListener(v-> addDataToFireStore());
    }

    public void loadRecyclerViewData() {
        if (tokenId != null){
            CollectionReference dbCourses = db.collection("Users").document(tokenId).collection(tokenId);
            dbCourses.orderBy("timeStamp", Query.Direction.ASCENDING).get().addOnSuccessListener(queryDocumentSnapshots -> {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {

                        String msg = document.getString("msg");
                        String viewType = document.getString("viewType");
                        String receiver = document.getString("receiver");
                        Timestamp tStamp = document.getTimestamp("timeStamp");

                        list.add(new ChatDemo(viewType, msg, receiver, tStamp));
                    }
                    adapter.notifyDataSetChanged();
                    listenForUpdates();
                } else {
                    Toast.makeText(getApplicationContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                }
                rcChat.smoothScrollToPosition(list.size());
            }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to load data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    private void addDataToFireStore() {
        if (!TextUtils.isEmpty(edtText.getText().toString())) {
            addDataToFirestore(edtText.getText().toString());
        }
    }

    private void addDataToFirestore(String msg) {
        CollectionReference dbCourses = db.collection("Users").document(tokenId).collection(tokenId);

        Timestamp currentTimestamp = Timestamp.now();

        ChatDemo chatDemo = new ChatDemo(Objects.requireNonNull(mAuth.getCurrentUser()).getUid(), msg.trim(), senderUid, currentTimestamp);

        dbCourses.add(chatDemo).addOnSuccessListener(documentReference -> {
            Toast.makeText(getApplicationContext(), "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            edtText.setText("");
        }).addOnFailureListener(e -> {
            Toast.makeText(getApplicationContext(), "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
        });
    }

    private void listenForUpdates() {
        CollectionReference dbCourses = db.collection("Users").document(tokenId).collection(tokenId);

        listenerRegistration = dbCourses.orderBy("timeStamp", Query.Direction.ASCENDING)
                .addSnapshotListener(this, (queryDocumentSnapshots, error) -> {
                    if (error != null) {
                        Log.e(TAG, "Listen failed: " + error);
                        return;
                    }

                    if (queryDocumentSnapshots != null) {
                        List<ChatDemo> updatedList = new ArrayList<>();
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            String msg = document.getString("msg");
                            String viewType = document.getString("viewType");
                            String receiver = document.getString("receiver");
                            Timestamp tStamp = document.getTimestamp("timeStamp");
                            updatedList.add(new ChatDemo(viewType, msg, receiver, tStamp));
                        }
                        list.clear();
                        list.addAll(updatedList);
                        adapter.notifyDataSetChanged();
                        rcChat.smoothScrollToPosition(list.size());
                    }
                });
    }

    static String usingRandomUUID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("_", "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }
}