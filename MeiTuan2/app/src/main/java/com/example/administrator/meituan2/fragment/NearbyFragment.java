package com.example.administrator.meituan2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.meituan2.R;

/**
 * Created by Administrator on 2016/10/15.
 */
public class NearbyFragment extends Fragment {

    public static NearbyFragment newInstance() {

        Bundle args = new Bundle();

        NearbyFragment fragment = new NearbyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_nearby, container, false);
        inflate.findViewById(R.id.tv_nearby);
        return inflate;
    }
}
