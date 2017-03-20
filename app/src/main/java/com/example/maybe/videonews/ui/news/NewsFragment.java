package com.example.maybe.videonews.ui.news;


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
public class NewsFragment extends Fragment {
    private TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_test,container,false);

            initView(view);
            initEvent();
            initData();
        return view;
    }

    private void initData() {
            textView.setText("在线新闻Fragment（待实现）");
    }

    private void initEvent() {
    }

    private void initView(View view) {
            textView= (TextView) view.findViewById(R.id.fragment_test_tv);
    }

}
