package com.example.admin.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.tester.R;

/**
 * Created by admin on 2016/3/22.
 */
public class EditMemoFragment extends Fragment {
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_memo_edit, container, false);
        //textView = (TextView) view.findViewById(R.id.textt);

        return view;
    }
}
