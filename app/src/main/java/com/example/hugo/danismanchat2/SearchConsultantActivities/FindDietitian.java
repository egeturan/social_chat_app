package com.example.hugo.danismanchat2.SearchConsultantActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hugo.danismanchat2.Builders.Contacts;
import com.example.hugo.danismanchat2.ProfileActivity;
import com.example.hugo.danismanchat2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FindDietitian extends AppCompatActivity {

    //variaables

    private Toolbar mToolbar;
    private RecyclerView FindFriendsRecyclerList;
    private DatabaseReference UsersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");


        FindFriendsRecyclerList = (RecyclerView) findViewById(R.id.find_friends_recycler_list);
        FindFriendsRecyclerList.setLayoutManager(new LinearLayoutManager(this));


        mToolbar = (Toolbar) findViewById(R.id.find_friends_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Diyetisyen Bul");
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Contacts> options =
                new FirebaseRecyclerOptions.Builder<Contacts>()
                        .setQuery(UsersRef, Contacts.class)
                        .build();


        FirebaseRecyclerAdapter<Contacts, FindFriendsActivity.FindFriendsViewHolder> adapter = new FirebaseRecyclerAdapter<Contacts, FindFriendsActivity.FindFriendsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FindFriendsActivity.FindFriendsViewHolder holder, final int position, @NonNull Contacts model) {
                holder.userName.setText(model.getName());
                holder.userStatus.setText(model.getStatus());
                Picasso.get().load(model.getImage()).placeholder(R.drawable.profile_image).into(holder.profileImage);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String visit_user_id = getRef(position).getKey();

                        Intent profileIntent = new Intent(FindDietitian.this, ProfileActivity.class);
                        profileIntent.putExtra("visit_user_id", visit_user_id);
                        startActivity(profileIntent);
                    }
                });

            }

            @NonNull
            @Override
            public FindFriendsActivity.FindFriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_display_layout, parent,false);
                FindFriendsActivity.FindFriendsViewHolder viewHolder = new FindFriendsActivity.FindFriendsViewHolder(view);

                return viewHolder;
            }
        };

        FindFriendsRecyclerList.setAdapter(adapter);

        adapter.startListening();

    }


    public static class FindFriendsViewHolder extends RecyclerView.ViewHolder
    {

        TextView userName, userStatus;
        CircleImageView profileImage;

        public FindFriendsViewHolder(View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.user_profile_name);
            userStatus = itemView.findViewById(R.id.user_status);
            profileImage = itemView.findViewById(R.id.users_profile_image);
        }
    }
}
