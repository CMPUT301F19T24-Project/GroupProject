package com.example.groupproject.ui.relations;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.groupproject.R;
import com.example.groupproject.User;

import java.util.ArrayList;

import static com.example.groupproject.MainActivity.TEMP_CACHED_USERS_LIST;

public class FollowersFragment extends Fragment {
    /**
     * @author: vivek
     * Handles displaying of fragment under Followers Tab
     */
    private ListView userListView;
    private ArrayAdapter<User> userArrayAdapter;
    private ArrayList<User> userFollowerList;

    public void onCreate(Bundle savedInstanceState) {
        /**
         * Initially create
         */
        super.onCreate(savedInstanceState);
        userFollowerList = new ArrayList<>();
        for (int i = 0; i < TEMP_CACHED_USERS_LIST.size(); i++){
            userFollowerList.add(i, TEMP_CACHED_USERS_LIST.get(i));
        }
        userArrayAdapter = new CustomUserList(this.getContext(), userFollowerList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        /**
         * Fills the listview with a users's follower.
         * @return root inflated view with filled list
         */
        View root = inflater.inflate(R.layout.fragment_relations, viewGroup, false);
        userListView = root.findViewById(R.id.users_list);
        TextView messageView = root.findViewById(R.id.section_label);
        messageView.setText("Once completed, will display user's followers");
        userListView.setAdapter(userArrayAdapter);
        return root;
    }
}
