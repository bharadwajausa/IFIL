package mobicrats.co.in.ifil.beans;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mobicrats.co.in.ifil.R;

public class ContactImageAdapter extends ArrayAdapter<ItemsDetails>{
Context context;
int layoutResourceId;
ArrayList<ItemsDetails> data=new ArrayList<ItemsDetails>();
public ContactImageAdapter(Context context, int layoutResourceId, ArrayList<ItemsDetails> data) {
super(context, layoutResourceId, data);
this.layoutResourceId = layoutResourceId;
this.context = context;
this.data = data;
}

@Override
public View getView(int position, View convertView, ViewGroup parent) {
View row = convertView;
ImageHolder holder = null;
if(row == null)
{
LayoutInflater inflater = ((Activity)context).getLayoutInflater();
row = inflater.inflate(layoutResourceId, parent, false);
holder = new ImageHolder();
holder.txtTitle = (TextView)row.findViewById(R.id.address);
    holder.txtDesc = (TextView)row.findViewById(R.id.desc);
    holder.txtDate = (TextView)row.findViewById(R.id.date);
    holder.s = (TextView)row.findViewById(R.id.s);
    holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);

row.setTag(holder);
}
else
{
holder = (ImageHolder)row.getTag();
}
ItemsDetails picture = data.get(position);
    try {
holder.txtTitle.setText(picture .Address);
    holder.txtDesc.setText(picture .Desc);
    holder.txtDate.setText(picture .date);
    holder.s.setText(picture .s);
//convert byte to bitmap take from contact class
byte[] outImage=picture.ProfilePic;

        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgIcon.setImageBitmap(theImage);
    }catch (Exception e){
        e.printStackTrace();
    }
return row;
}
static class ImageHolder
{
ImageView imgIcon;
TextView txtTitle;
    TextView txtDesc;
    TextView txtDate;
    TextView s;
}
}




