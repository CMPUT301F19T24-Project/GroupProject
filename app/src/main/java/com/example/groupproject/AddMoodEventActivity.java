package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddMoodEventActivity extends AppCompatActivity {

    ListView moodEventList;
    ListMoodEventsAdapter moodEventAdapter;
    ArrayList<MoodEvent> moodEventDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_current_mood);
        System.out.println("AddMoodEventActivity");
        initalize();
    }

    private void initalize()
    {
        moodEventList = findViewById(R.id.moodEventList);
        moodEventDataList = new ArrayList<>();
        moodEventAdapter = new ListMoodEventsAdapter(this, moodEventDataList, SortingMethod.DATE);
        moodEventList.setAdapter(moodEventAdapter);
        moodEventList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.info_about_mood, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                MoodEvent curMoodEvent = (MoodEvent) moodEventList.getItemAtPosition(i);

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                popupWindow.setOutsideTouchable(true);
//
//                LinearLayout ll = popupView.findViewById(R.id.ll_detail_header);
//                TextView tv_moodName = popupView.findViewById(R.id.tv_mood_name_details);
//                TextView tv_timeStamp = popupView.findViewById(R.id.tv_time_stamp_details);
//                Spinner s_socialSituation = popupView.findViewById(R.id.s_details_social_situation);
//                EditText et_desc = popupView.findViewById(R.id.tv_desc);
//                ImageView iv_desc = popupView.findViewById(R.id.iv_img_desc);
//                MapView mv_map = popupView.findViewById(R.id.mv_detail_map_view);
//
//                Button b_apply = popupView.findViewById(R.id.b_apply);
//                Button b_delete = popupView.findViewById(R.id.b_apply);
//                Button b_uploadImage = popupView.findViewById(R.id.b_upload_img);

//
//                ll.setBackgroundColor(curMoodEvent.getMood().getColor());
//                tv_moodName.setText(curMoodEvent.getMood().getName());
//                tv_timeStamp.setText(String.format("%d-%d-%d",
//                        curMoodEvent.getTimeStamp().get(Calendar.DATE),
//                        curMoodEvent.getTimeStamp().get(Calendar.MONTH),
//                        curMoodEvent.getTimeStamp().get(Calendar.YEAR)));


            }

        });

        if (true) {
            moodEventDataList.add(new MoodEvent(new Happy(), new GregorianCalendar(1, 2, 3), SocialSituation.NONE, "ABC", null, null, 0));
            moodEventDataList.add(new MoodEvent(new Sad(), new GregorianCalendar(2017,06,03), SocialSituation.NONE, "ABC", null, null, 0));
            moodEventDataList.add(new MoodEvent(new Angry(), new GregorianCalendar(2017,06,04), SocialSituation.NONE, "ABC", null, null, 0));
            moodEventDataList.add(new MoodEvent(new Anxious(), new GregorianCalendar(2019,11,10), SocialSituation.NONE, "ABC", null, null, 0));
            moodEventDataList.add(new MoodEvent(new Disgusted(), new GregorianCalendar(2015,01,02), SocialSituation.NONE, "ABC", null, null, 0));
        }

    }
}
