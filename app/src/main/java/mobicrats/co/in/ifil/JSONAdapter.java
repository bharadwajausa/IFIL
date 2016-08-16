package mobicrats.co.in.ifil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mobicrats.co.in.ifil.beans.ItemsDetails;

/**
 * Created by Personal on 21-06-2016.
 */
public class JSONAdapter {
    static final String DATABASE_NAME1 = "json.db";
    static final int DATABASE_VERSION1 = 1;
    public static final int NAME_COLUMN1 = 1;
    String jsonString="";
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.


    static final String DATABASE_CREATE_FOR_JSON = "create table "+"JSON1"+
            "( " +"ID"+" integer primary key autoincrement,"+ "address  text,desc  text" +
            ",Date  text,s  text,pic  BLOB); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private JsonDatabaseHelper dbHelper;
    public  JSONAdapter(Context _context)
    {
        context = _context;
        dbHelper = new JsonDatabaseHelper(context, DATABASE_NAME1, null, DATABASE_VERSION1);
    }
    public JSONAdapter open1() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }


    public void insertString( String address, String desc, String Date, String s,byte[] pic){
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
       // newValues.put("landmark", landmark);
        newValues.put("address", address);
        newValues.put("desc", desc);
        newValues.put("Date", Date);
        newValues.put("s", s);
        newValues.put("pic", pic);

        // Insert the row into your table
        db.insert("JSON1", null, newValues);
    }

    public List<ItemsDetails> getAllContacts() {
        List<ItemsDetails> contactList = new ArrayList<ItemsDetails>();

        Cursor cursor = db.rawQuery("SELECT * from JSON1", null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ItemsDetails contact = new ItemsDetails();
                contact.setUserID(cursor.getString(0));
                contact.setAddress(cursor.getString(1));
                contact.setDesc(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setS(cursor.getString(4));
                contact.setProfilePic(cursor.getBlob(5));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }
    public List<ItemsDetails> getFoundItems() {
        List<ItemsDetails> contactList = new ArrayList<ItemsDetails>();

        Cursor cursor = db.rawQuery("SELECT * from JSON1 where s='Ifound'", null);//
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ItemsDetails contact = new ItemsDetails();
                contact.setUserID(cursor.getString(0));
                contact.setAddress(cursor.getString(1));
                contact.setDesc(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setS(cursor.getString(4));
                contact.setProfilePic(cursor.getBlob(5));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }
    public List<ItemsDetails> getLostItems() {
        List<ItemsDetails> contactList = new ArrayList<ItemsDetails>();

        Cursor cursor = db.rawQuery("SELECT * from JSON1 where s='ILost'", null);//
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ItemsDetails contact = new ItemsDetails();
                contact.setUserID(cursor.getString(0));
                contact.setAddress(cursor.getString(1));
                contact.setDesc(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setS(cursor.getString(4));
                contact.setProfilePic(cursor.getBlob(5));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }


}
