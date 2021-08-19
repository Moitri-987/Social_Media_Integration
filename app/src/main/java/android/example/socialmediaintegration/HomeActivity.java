package android.example.socialmediaintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Button signout;
    TextView tsf, username,userEmail;
    ImageView logo, user_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tsf=findViewById(R.id.tsf);
        logo=findViewById(R.id.logo);
        user_profile=findViewById(R.id.user_profile);
        username=findViewById(R.id.username);
        userEmail=findViewById(R.id.userEmail);
        signout=findViewById(R.id.logOut);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
            }
        });

        if (mUser!=null){
            if(mUser.getDisplayName()!=null) {
                username.setText(mUser.getDisplayName());
            }else{
                username.setText("User Name");
            }
            userEmail.setText(mUser.getEmail());
            if (mUser.getPhotoUrl()!=null){
                String photoUrl=mUser.getPhotoUrl().toString();
                photoUrl = photoUrl+"?type=large";
                Glide.with(this).load(photoUrl).into(user_profile);
            }else{
                user_profile.setImageResource(R.drawable.user_profile);
            }
        }else {
            username.setText("No Name");
            userEmail.setText("No Email");
            user_profile.setImageResource(R.drawable.user_profile);
        }
    }
}
