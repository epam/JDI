package com.epam.jdi.jditestandroidapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.epam.jdi.jditestandroidapp.R;
import com.epam.jdi.jditestandroidapp.base.BackFragment;

/**
 * Created by Vitalii_Sokolov on 6/2/2016.
 */
public class ContactFormFragment extends BackFragment implements View.OnClickListener {

    Button mSubmit;
    Button mCalculate;

    RadioGroup mLeft;
    RadioGroup mRight;

    public static Fragment newInstance() {
        return new ContactFormFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSubmit = (Button) view.findViewById(R.id.submit_btn);
        mCalculate = (Button) view.findViewById(R.id.calculate_btn);
        mLeft = (RadioGroup) view.findViewById(R.id.radio1);
        mRight = (RadioGroup) view.findViewById(R.id.radio2);

        mSubmit.setOnClickListener(this);
        mCalculate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_btn) {

        } else {

        }
    }
}
