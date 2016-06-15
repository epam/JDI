package com.epam.jdi.jditestandroidapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

    EditText mName;
    EditText mLastName;
    EditText mDescr;

    TextView mResult;

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
        mResult = (TextView) view.findViewById(R.id.result);
        mName = (EditText) view.findViewById(R.id.input_name);
        mLastName = (EditText) view.findViewById(R.id.input_last_name);
        mDescr = (EditText) view.findViewById(R.id.input_descr);

        mSubmit.setOnClickListener(this);
        mCalculate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        RadioButton r1 = (RadioButton) getView().findViewById(mLeft.getCheckedRadioButtonId());
        int r1Value = Integer.parseInt(r1.getText().toString());
        RadioButton r2 = (RadioButton) getView().findViewById(mRight.getCheckedRadioButtonId());
        int r2Value = Integer.parseInt(r2.getText().toString());

        mResult.setText(getString(R.string.contact_form_result_format,
                mName.getText(),
                mLastName.getText(),
                mDescr.getText(),
                r1Value + r2Value));
    }
}
