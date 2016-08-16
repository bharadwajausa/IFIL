package mobicrats.co.in.ifil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class RegistrationActivity extends AppCompatActivity {
EditText reg_fullname,reg_password;
    Button btnRegister;
    TextView link_to_login;

    DataBaseAdapter dataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        reg_fullname=(EditText)findViewById(R.id.reg_fullname);
        reg_password=(EditText)findViewById(R.id.reg_password);

        link_to_login=(TextView)findViewById(R.id.link_to_login);

        btnRegister=(Button) findViewById(R.id.btnRegister);

        dataBaseAdapter=new DataBaseAdapter(this);
        try {
            dataBaseAdapter=dataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=reg_fullname.getText().toString();
                String password=reg_password.getText().toString();

                // check if any of the fields are vaccant
                if(userName.equals("")||password.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                /*// check if both password matches
                if(!password.equals(reg_password))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }*/
                else
                {
                    // Save the Data in Database
                    dataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }
            }

        });
        link_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(re);
            }
        });
    }
}
