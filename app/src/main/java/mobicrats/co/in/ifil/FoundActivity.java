package mobicrats.co.in.ifil;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mobicrats.co.in.ifil.beans.ContactImageAdapter;
import mobicrats.co.in.ifil.beans.ItemsDetails;

public class FoundActivity extends AppCompatActivity {
    final Context context = this;


    ListView dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);
    }
    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        super.onBackPressed();
        finish();
    }
    public void onResume(){
        ArrayList<ItemsDetails> imageArry = new ArrayList<ItemsDetails>();

        ContactImageAdapter adapter;
        JSONAdapter dataBaseAdapter1;
        dataBaseAdapter1=new JSONAdapter(this);
        try {
            dataBaseAdapter1=dataBaseAdapter1.open1();

            List<ItemsDetails> contacts = dataBaseAdapter1.getFoundItems();
            for (ItemsDetails cn : contacts) {
                String log = "ID:" + cn.getUserID() + " Address: " + cn.getAddress()
                        + " ,Desc: " + cn.getDesc()+ " ,Date: " + cn.getDate()+ " ,S: "
                        + cn.getS()+ " ,ProfilePic: " + cn.getProfilePic();

// Writing Contacts to log
                //Log.d("Result: ", log);
                imageArry.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter = new ContactImageAdapter(this, R.layout.item_holder,
                imageArry);
        dataList = (ListView) findViewById(R.id.list_view_found);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView v = (TextView) dataList.findViewById(R.id.desc);
                Toast.makeText(getApplicationContext(),v.getText().toString(),Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(getApplicationContext(),SingleItemViewActivity.class));
            }
        });
        dataList.setAdapter(adapter);
        super.onResume();
    }
}
