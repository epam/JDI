package com.epam.jdi.jditestandroidapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.epam.jdi.jditestandroidapp.R;
import com.epam.jdi.jditestandroidapp.base.BackFragment;



/**
 * Created by vitalii_sokolov on 08.06.16.
 */
public class ServicePickerFragment extends BackFragment implements AdapterView.OnItemClickListener, RatingBar.OnRatingBarChangeListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    Spinner mSpinner;
    TextView mResult;
    SeekBar mSeekBar;
    RatingBar mRatingBar;
    Switch mSwitchBtn;

    String[] data = {"one", "two", "three", "four", "five"};
    private float mRateValue;
    private int mSeekValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.picker_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mResult = (TextView) view.findViewById(R.id.result);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        mSwitchBtn = (Switch) view.findViewById(R.id.switchBtn);

        mSeekBar.setOnSeekBarChangeListener(this);
        mRatingBar.setOnRatingBarChangeListener(this);
        mSpinner.setOnItemSelectedListener(this);
        mSwitchBtn.setOnCheckedChangeListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);
        mSpinner.setSelection(2);


    }

    public static Fragment newInsance() {
        return new ServicePickerFragment();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        printResult();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        mRateValue = rating;
        printResult();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSeekValue = progress;
        printResult();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void printResult() {

        mResult.setText(getContext().getString(R.string.pick_mask,
                mSpinner.getSelectedItem().toString(),
                mSeekValue,
                String.format("%.01f", mRateValue),
                mSwitchBtn.isChecked()+""));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        printResult();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        printResult();
    }
}
