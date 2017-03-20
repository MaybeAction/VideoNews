package com.example.maybe.videonews.ui.local;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.maybe.videonews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalVideoFragment extends Fragment {
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test,container,false);
        textView= (TextView) view.findViewById(R.id.fragment_test_tv);
        textView.setText("本地视频Fragment（待实现）");
        return view;
    }

}
