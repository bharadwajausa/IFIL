package mobicrats.co.in.ifil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mobicrats.co.in.ifil.beans.ContactImageAdapter;
import mobicrats.co.in.ifil.beans.Ifound_Get_FoundItemDetails_Result;
import mobicrats.co.in.ifil.beans.ItemsDetails;


public class ILostFragment extends Fragment {
    JSONAdapter dataBaseAdapter1;
    ArrayList<ItemsDetails> imageArry = new ArrayList<ItemsDetails>();

    ContactImageAdapter adapter;


    public ILostFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ilost, container, false);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        dataBaseAdapter1=new JSONAdapter(getActivity());
        try {
            dataBaseAdapter1=dataBaseAdapter1.open1();

        List<ItemsDetails> contacts = dataBaseAdapter1.getAllContacts();
        for (ItemsDetails cn : contacts) {
            String log = "ID:" + cn.getUserID() + " Address: " + cn.getAddress()
                    + " ,Desc: " + cn.getDesc()+ " ,Date: " + cn.getDate()+ " ,S: "
                    + cn.getS()+ " ,ProfilePic: " + cn.getProfilePic();

// Writing Contacts to log
            Log.d("Result S: ", cn.getS());
            imageArry.add(cn);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new ContactImageAdapter(getActivity(), R.layout.item_holder,
                imageArry);
        ListView dataList = (ListView) rootView.findViewById(R.id.i_lost_listView);
        dataList.setAdapter(adapter);

        return rootView;
    }

}
