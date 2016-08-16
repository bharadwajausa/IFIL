package mobicrats.co.in.ifil;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mobicrats.co.in.ifil.beans.ItemsDetails;

/**
 * Created by Personal on 20-06-2016.
 */
public class AddIFoundItems extends Activity {
    String ba1;
    public static final int MEDIA_TYPE_IMAGE = 1;
    InputStream inputStream;

    private Uri fileUri; // file url to store image/video
    ImageView add_i_foundImage,date_picker;
    Button view_add_i_foundImage;
    static String s="Ifound";

    public static Calendar c = Calendar.getInstance();
    public static SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    public static String formattedDate = df.format(c.getTime());

    private static EditText found_date;
    EditText emailid,description;
    Button submit;

    BufferedInputStream buf;


    byte[] byteArray;

    JSONAdapter dataBaseAdapter1;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vc);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        dataBaseAdapter1=new JSONAdapter(this);
        try {
            dataBaseAdapter1=dataBaseAdapter1.open1();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //landmark=(EditText)findViewById(R.id.landmark);
        emailid=(EditText)findViewById(R.id.emailid);
        description=(EditText)findViewById(R.id.description);

        found_date=(EditText)findViewById(R.id.found_date);

        date_picker=(ImageView)findViewById(R.id.date_picker4);
        date_picker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getFragmentManager(), "datePicker");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        add_i_foundImage=(ImageView)findViewById(R.id.image);
        view_add_i_foundImage=(Button)findViewById(R.id.choose_file);
        view_add_i_foundImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                selectImage();
                //	add_i_foundImage.setVisibility(View.VISIBLE);
            }
        });
        submit=(Button)findViewById(R.id.submit19);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (description.getText().toString().equals("") || emailid.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill All Fields!", Toast.LENGTH_LONG).show();


                } else {

                    //System.out.println("String is"+request);
                    //Log.d("String===",request);
                    //Toast.makeText(getApplicationContext(), request, Toast.LENGTH_LONG).show();
                    dataBaseAdapter1.insertString(emailid.getText().toString(), description.getText().toString(), formattedDate.toString(),
                            "Ifound", byteArray);
                    Toast.makeText(getApplicationContext(), "Your Item is Added Successfully!", Toast.LENGTH_LONG).show();
                    finish();
                    //new AddIFoundMethod().execute(request);
                    //}
                    /*Intent k_return = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(k_return);
                    finish();*/
                }
            }
            private Object inputStreamToString(java.io.InputStream is) {
                // TODO Auto-generated method stub
                String rLine = "";
                StringBuilder answer = new StringBuilder();

                InputStreamReader isr = new InputStreamReader(is);

                BufferedReader rd = new BufferedReader(isr);

                try {
                    while ((rLine = rd.readLine()) != null) {
                        answer.append(rLine);
                    }
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
                return answer;
            }
        });


       // System.out.println("db is"+f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar actions click
        switch (item.getItemId()) {
            case android.R.id.home:
                //this.finish();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceSateate) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen
            found_date.setText(checkDigit(month+1)+"-"+checkDigit(day)+"-"+year
            );
            formattedDate = checkDigit(month+1)+"-"+checkDigit(day)+"-"+year
            ;
        }
        public String checkDigit(int number)
        {
            return number<=9?"0"+number:String.valueOf(number);
        }
    }

    protected void selectImage() {
        // TODO Auto-generated method stub
        final CharSequence[] options = { "Choose from Gallery",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddIFoundItems.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    ByteArrayInputStream in = new ByteArrayInputStream(stream.toByteArray());
                    buf = new BufferedInputStream(in);
                    byteArray = stream.toByteArray();

                    ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    add_i_foundImage.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "LostAndFound" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");

                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.MediaColumns.DATA };
                Cursor c = getContentResolver().query(
                        selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                /*Log.w("path of image from gallery......******************.........",
                        picturePath + "");*/
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                ByteArrayInputStream in = new ByteArrayInputStream(stream.toByteArray());
                buf = new BufferedInputStream(in);
                byteArray = stream.toByteArray();

                ba1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                add_i_foundImage.setVisibility(View.VISIBLE);
                add_i_foundImage.setImageBitmap(thumbnail);
                path = android.os.Environment
                        .getExternalStorageDirectory()
                        + File.separator
                        + "Lost and Found" + File.separator + "default";

            }
        }


    }
    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        super.onBackPressed();
    }

}
