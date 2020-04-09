package com.e.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.e.fight_corona.models.People;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity
{
    CardView medicalSupport,news_analyser,latestNews,support,about,logout;
    FirebaseUser fuser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);







        medicalSupport=findViewById(R.id.notes_card);
        medicalSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(Home.this,Medic_home.class);
                startActivity(i);

            }
        });
        news_analyser=findViewById(R.id.news_analyser);
        news_analyser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(Home.this,News_Analyse_Home.class);
                startActivity(i);
            }
        });
        latestNews=findViewById(R.id.corona_news);
        latestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(Home.this,corono_news.class);
                startActivity(i);

            }
        });
        about=findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                big_picturestyle_Notification();
                Intent i= new Intent(Home.this,About.class);
                startActivity(i);

            }
        });
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i= new Intent(Home.this,startup.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        support=findViewById(R.id.Supporting_candidate);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fuser= FirebaseAuth.getInstance().getCurrentUser();
                reference= FirebaseDatabase.getInstance().getReference("Volunteers").child(fuser.getUid());
                reference.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        People people=dataSnapshot.getValue(People.class);
                        if(people==null)
                        {
                            Intent i= new Intent(Home.this,Suport.class);
                            startActivity(i);
                        }
                        else
                        {
                            Intent i= new Intent(Home.this,Volunteer.class);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }


    private void big_picturestyle_Notification() {
        int notificationId = 2;
        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.handwashing);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
                .setContentTitle("Time to wash your hands ")
                //style = BigPictureStyle
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(picture))
                .setAutoCancel(true);

        Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(path);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "YOUR_CHANNEL_ID";
            NotificationChannel channel = new NotificationChannel(channelId,"Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        notificationManager.notify(notificationId, builder.build());
    }
}
