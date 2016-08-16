package mobicrats.co.in.ifil;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mobicrats.co.in.ifil.beans.ContactImageAdapter;
import mobicrats.co.in.ifil.beans.ItemsDetails;

public class Main2Activity extends AppCompatActivity {

    private ViewPager mViewPager;
    final Context context = this;

    ArrayList<ItemsDetails> imageArry = new ArrayList<ItemsDetails>();

    ContactImageAdapter adapter;
    JSONAdapter dataBaseAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Lost And Found Items", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // TODO Auto-generated method stub
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Please select Your Choice!"); //Set Alert dialog title here
                //alert.setMessage("Enter Your Name Here"); //Message here


                alert.setPositiveButton("I FOUND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //You will get as string input data in this variable.
                        // here we convert the input to a string and show in a toast.
                        Intent ifo = new Intent(getApplicationContext(), AddIFoundItems.class);
                        startActivity(ifo);
                        //Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                    } // End of onClick(DialogInterface dialog, int whichButton)
                }); //End of alert.setPositiveButton
                alert.setNegativeButton("I LOST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                        // dialog.cancel();
                        Intent ilo = new Intent(getApplicationContext(), AddILostItems.class);
                        startActivity(ilo);
                    }
                }); //End of alert.setNegativeButton
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
	       /* Alert Dialog Code End*/
            }// End of onClick(View v)

        });

        //Log.d("data json",getJson+"/n");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_lost) { //filter for lost context
            startActivity(new Intent(getApplicationContext(),LostActivity.class));
            return true;
        }
        if (id == R.id.action_found) { //filter for found context
            startActivity(new Intent(getApplicationContext(),FoundActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        super.onBackPressed();
        finish();
    }
    public void onResume(){
        dataBaseAdapter1=new JSONAdapter(this);
        try {
            dataBaseAdapter1=dataBaseAdapter1.open1();

            List<ItemsDetails> contacts = dataBaseAdapter1.getAllContacts();
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
        ListView dataList = (ListView) findViewById(R.id.list_view);
        dataList.setAdapter(adapter);
        super.onResume();
    }
}

