package com.e.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class foodactivity extends AppCompatActivity
{
    private EditText name,number,foodcapactity,location;
    private Spinner hrs,min,dd,mm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodactivity);
        name=findViewById(R.id.name);
        number=findViewById(R.id.number);
        foodcapactity=findViewById(R.id.foodcapacity);
        location=findViewById(R.id.location);
        hrs=findViewById(R.id.hrs);
        min=findViewById(R.id.min);
        dd=findViewById(R.id.date);
        mm=findViewById(R.id.mm);



    }
    public void submit1(View v)
    {
        String name_str,number_str,food,hrs_str,min_str,dd_str,mm_str,loc;
        name_str=name.getText().toString();
        number_str=number.getText().toString();
        food=foodcapactity.getText().toString();
        hrs_str=hrs.getSelectedItem().toString();
        min_str=min.getSelectedItem().toString();
        dd_str=dd.getSelectedItem().toString();
        mm_str=mm.getSelectedItem().toString();
        loc=location.getText().toString();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        final HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("name",name_str);
        hashMap.put("mobile_no",number_str);
        hashMap.put("food_capacity",food);
        hashMap.put("time", hrs_str+":"+min_str);
        hashMap.put("Date",dd_str+" "+mm_str);
        hashMap.put("location",loc);


        DatabaseReference x =reference.child("Food Orders").push();
        hashMap.put("id", x.getKey());
        x.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(foodactivity.this,"Order has been placed",Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(foodactivity.this,Home.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(foodactivity.this,"Order failed to post",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
