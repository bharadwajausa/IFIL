package mobicrats.co.in.ifil;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
TextView notamember;
    EditText user_name,password;
    Button submit;

    DataBaseAdapter dataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        makeFullScreen();

        dataBaseAdapter=new DataBaseAdapter(this);
        try {
            dataBaseAdapter=dataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       /* String g=dataBaseAdapter.getSinlgeEntry(userName);
        Toast.makeText(getApplicationContext(), g, Toast.LENGTH_LONG).show();*/

        user_name=(EditText)findViewById(R.id.user_name);
        password=(EditText)findViewById(R.id.password);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_un=user_name.getText().toString();
                String s_pass=password.getText().toString();

                String g=dataBaseAdapter.getSinlgeEntry(s_un);

                if (g.equals("1")){
                    Toast.makeText(getApplicationContext(), "NOT EXIST!", Toast.LENGTH_LONG).show();
                }
                else if(s_pass.equals(g)){
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();

                    Intent ho=new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(ho);
                }


            }
        });


        notamember=(TextView)findViewById(R.id.not_a_member);
        notamember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(i);
            }
        });


    }
    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }
}
