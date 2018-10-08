package com.example.adrianzatreanu.monthslayout;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by adrianzatreanu on 23/11/2016.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private TreeMap<Month, ArrayList<Run>> runs;
    private ArrayList<Object> data;
    private ArrayList<Integer> types;
    private LayoutInflater inflater;
    private final static int NUMBER_OF_ROW_TYPES = 2;
    private final static int RUN_TYPE = 1;
    private final static int HEADER_TYPE = 0;
    private HashMap<Integer, String> monthsMap = new HashMap<>();

    private static class RunRowHolder{
        private TextView dateTextView;
        private TextView distanceTextView;
        private TextView paceView;
        private TextView timeView;
        private TextView distanceKM;
        private ImageView firstIcon;
        private ImageView secondIcon;
        private ImageView thirdIcon;
        private ImageView fourthIcon;

        public RunRowHolder(TextView dateTextView, TextView distanceTextView,
                         TextView paceView, TextView timeView, TextView distanceKM, ImageView firstIcon,
                            ImageView secondIcon, ImageView thirdIcon, ImageView fourthIcon){
            this.dateTextView = dateTextView;
            this.distanceTextView = distanceTextView;
            this.paceView = paceView;
            this.timeView = timeView;
            this.distanceKM = distanceKM;
            this.firstIcon = firstIcon;
            this.secondIcon = secondIcon;
            this.thirdIcon = thirdIcon;
            this.fourthIcon = fourthIcon;
        }
    }

    private static class HeaderRowHolder{
        private TextView monthTextView;
        private TextView distanceTextView;
        private TextView paceView;
        private TextView nikeFuelView;

        public HeaderRowHolder(TextView monthTextView, TextView distanceTextView,
                         TextView paceView, TextView nikeFuelView){
            this.monthTextView = monthTextView;
            this.distanceTextView = distanceTextView;
            this.paceView = paceView;
            this.nikeFuelView = nikeFuelView;
        }
    }

    public CustomAdapter(Context context, TreeMap<Month, ArrayList<Run>> runs){
        super();
        this.context = context;
        this.runs = runs;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mapToList();
        initializeMonthsMap();
    }

    private void mapToList(){
        data = new ArrayList<>();
        types = new ArrayList<>();
        for(TreeMap.Entry<Month, ArrayList<Run>> entry : runs.entrySet()) {
            Month key = entry.getKey();
            ArrayList<Run> value = entry.getValue();
            data.add(key);
            data.addAll(value);
            types.add(HEADER_TYPE);
            for(int i = 0; i < value.size(); i++){
                types.add(RUN_TYPE);
            }
        }
    }

    private void initializeMonthsMap(){
        this.monthsMap.put(12, "December");
        this.monthsMap.put(11, "November");
        this.monthsMap.put(10, "October");
        this.monthsMap.put(9, "September");
        this.monthsMap.put(8, "August");
        this.monthsMap.put(7, "July");
        this.monthsMap.put(6, "June");
        this.monthsMap.put(5, "May");
        this.monthsMap.put(4, "April");
        this.monthsMap.put(3, "March");
        this.monthsMap.put(2, "February");
        this.monthsMap.put(1, "January");
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

        switch(getItemViewType(i)){

            // Run
            case RUN_TYPE:

                RunRowHolder runRowHolder;

                if(view == null){
                    view = this.inflater.inflate(R.layout.run_layout, null);

                    TextView distance = (TextView) view.findViewById(R.id.distance_run);
                    TextView pace = (TextView) view.findViewById(R.id.pace);
                    TextView time = (TextView) view.findViewById(R.id.time);
                    TextView date = (TextView) view.findViewById(R.id.date);
                    TextView distanceKM = (TextView) view.findViewById(R.id.text_km);

                    ImageView firstIcon = (ImageView) view.findViewById(R.id.icon1);
                    ImageView secondIcon = (ImageView) view.findViewById(R.id.icon2);
                    ImageView thirdIcon = (ImageView) view.findViewById(R.id.icon3);
                    ImageView fourthIcon = (ImageView) view.findViewById(R.id.icon4);

                    runRowHolder = new RunRowHolder(date, distance, pace, time, distanceKM,
                                firstIcon, secondIcon, thirdIcon, fourthIcon);

                    view.setTag(runRowHolder);
                }

                runRowHolder = (RunRowHolder) view.getTag();

                Run run = (Run) data.get(i);
                runRowHolder.dateTextView.setText(run.getDay() + "/" + run.getMonth() + "/" + run.getYear());
                runRowHolder.distanceKM.setText("km");
                runRowHolder.distanceTextView.setText(run.getDistance());
                Typeface typeface = Typeface.createFromAsset(this.context.getAssets(), "fonts/FallingSky.otf");
                runRowHolder.distanceTextView.setTypeface(typeface);
                runRowHolder.distanceKM.setTypeface(typeface);
                runRowHolder.paceView.setText(run.getPace());
                runRowHolder.timeView.setText(run.getTime());

                //setup icons
                String[] icons = run.getIcons();
                if(icons[0] == null)
                    runRowHolder.firstIcon.setVisibility(View.GONE);
                else if(icons[0].equals("number1"))
                    runRowHolder.firstIcon.setImageResource(R.mipmap.number);
                else if (icons[0].equals("number3"))
                    runRowHolder.firstIcon.setImageResource(R.mipmap.number3);


                if(icons[1] == null)
                    runRowHolder.secondIcon.setVisibility(View.GONE);
                else if(icons[1].equals("smiley"))
                    runRowHolder.secondIcon.setImageResource(R.mipmap.smiley);
                else if (icons[1].equals("sad"))
                    runRowHolder.secondIcon.setImageResource(R.mipmap.sad);

                if(icons[2] == null)
                    runRowHolder.thirdIcon.setVisibility(View.GONE);
                else if(icons[2].equals("track"))
                    runRowHolder.thirdIcon.setImageResource(R.mipmap.track);
                else if (icons[2].equals("highway"))
                    runRowHolder.thirdIcon.setImageResource(R.mipmap.highway);

                if(icons[3] == null)
                    runRowHolder.fourthIcon.setVisibility(View.GONE);
                if(icons[3].equals("sun"))
                    runRowHolder.fourthIcon.setImageResource(R.mipmap.sun);
                else if (icons[3].equals("rain"))
                    runRowHolder.fourthIcon.setImageResource(R.mipmap.rain);

                break;

            // Header
            case HEADER_TYPE:

                HeaderRowHolder headerRowHolder;

                if(view == null) {
                    view = this.inflater.inflate(R.layout.header_layout, null);

                    TextView month = (TextView) view.findViewById(R.id.month);
                    TextView totalDistance = (TextView) view.findViewById(R.id.distance);
                    TextView averagePace = (TextView) view.findViewById(R.id.pace);
                    TextView nikeFuel = (TextView) view.findViewById(R.id.nike_fuel);

                    headerRowHolder = new HeaderRowHolder(month, totalDistance, averagePace,
                            nikeFuel);

                    view.setTag(headerRowHolder);
                }

                headerRowHolder = (HeaderRowHolder) view.getTag();

                Month monthObject = (Month) data.get(i);

                headerRowHolder.monthTextView.setText(this.monthsMap.get(monthObject.getMonth()));
                headerRowHolder.distanceTextView.setText(monthObject.getTotalDistance());
                headerRowHolder.paceView.setText(monthObject.getAveragePace());
                headerRowHolder.nikeFuelView.setText(monthObject.getNikeFuel());

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
        if(types.get(position) == RUN_TYPE)
            return RUN_TYPE;
        return HEADER_TYPE;
    }
}
