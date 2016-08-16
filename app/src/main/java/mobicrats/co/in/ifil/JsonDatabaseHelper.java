package mobicrats.co.in.ifil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Personal on 21-06-2016.
 */
public class JsonDatabaseHelper extends SQLiteOpenHelper {

    public JsonDatabaseHelper(Context context1, String name1, SQLiteDatabase.CursorFactory factory1, int version1) {
        super(context1, name1, factory1, version1);
    }

    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase _db1) {
        _db1.execSQL(JSONAdapter.DATABASE_CREATE_FOR_JSON);

    }

    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db1, int _oldVersion1, int _newVersion1) {
        // Log the version upgrade.
        Log.w("LoginDataBaseAdapter", "Upgrading from version " + _oldVersion1 + " to " + _newVersion1 + ", which will destroy all old data");


        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db1.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(_db1);
    }
}