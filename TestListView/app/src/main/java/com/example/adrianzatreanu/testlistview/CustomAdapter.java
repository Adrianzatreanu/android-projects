package com.example.adrianzatreanu.testlistview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by adrianzatreanu on 23/11/2016.
 */

public class CustomAdapter extends BaseAdapter {

    private List<String> data;
    private LayoutInflater inflater;
    private final static int NUMBER_OF_ROW_TYPES = 2;
    private static final int EVEN_ROW_TYPE = 0;
    private final static int ODD_ROW_TYPE = 1;

    private static class RowHolder{
        private TextView textView;
        private ImageView imageView;

        public RowHolder(ImageView imageView, TextView textView){
            this.textView = textView;
            this.imageView=  imageView;
        }
    }

    public CustomAdapter(Context context, List<String> data){
        super();
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        RowHolder rowHolder;
        LayoutInflater inflater;

        if(view == null) {

            TextView textView;
            ImageView imageView;

            view = this.inflater.inflate(R.layout.row_layout, null);
            textView = (TextView) view.findViewById(R.id.custom_text_view);
            imageView = (ImageView) view.findViewById(R.id.image_view);
            rowHolder = new RowHolder(imageView, textView);

            view.setTag(rowHolder);
        }

        rowHolder = (RowHolder) view.getTag();

        rowHolder.textView.setText(data.get(i));
        rowHolder.imageView.setImageResource(R.mipmap.ic_launcher);

        switch(getItemViewType(i)){
            case EVEN_ROW_TYPE:
                view.setBackgroundColor(Color.RED);
                break;
            case ODD_ROW_TYPE:
                view.setBackgroundColor(Color.BLUE);
                break;
        }

        return view;
    }

    @Override
    public int getViewTypeCount(){
        return NUMBER_OF_ROW_TYPES;
    }

    @Override
    public int getItemViewType(int position){
        return position % 2;
    }

}
