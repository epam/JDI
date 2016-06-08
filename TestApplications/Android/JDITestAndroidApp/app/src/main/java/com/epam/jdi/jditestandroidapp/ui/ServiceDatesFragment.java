package com.epam.jdi.jditestandroidapp.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.epam.jdi.jditestandroidapp.R;
import com.epam.jdi.jditestandroidapp.base.BackFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Vitalii_Sokolov on 6/2/2016.
 */
public class ServiceDatesFragment extends BackFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private View mDateLayout;
    private View mTimeLayout;
    private TextView mDateTextView;
    private TextView mTimeTextView;

    private Calendar date;
    private Calendar time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDateLayout = view.findViewById(R.id.date);
        mTimeLayout = view.findViewById(R.id.time);

        mDateTextView = (TextView) view.findViewById(R.id.date_text);
        mTimeTextView = (TextView) view.findViewById(R.id.time_text);

        mDateLayout.setOnClickListener(this);
        mTimeLayout.setOnClickListener(this);

        if (savedInstanceState != null) {
            time = (Calendar) savedInstanceState.getSerializable("time");
            date = (Calendar) savedInstanceState.getSerializable("date");

            if (time != null) {
                setDate(DateFormat.getTimeInstance(), time.getTime(), mTimeTextView);
            }
            if (date != null) {
                setDate(DateFormat.getDateInstance(), date.getTime(), mDateTextView);
            }
        }
    }

    private void setDate(DateFormat instance, Date dateTime, TextView textView) {
        textView.setText(instance.format(dateTime));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.date) {
            if (date == null) {
                date = Calendar.getInstance();
            }
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    ServiceDatesFragment.this,
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        } else {
            if (time == null) {
                time = Calendar.getInstance();
            }
            TimePickerDialog datePickerDialog = new TimePickerDialog(getContext(),
                    ServiceDatesFragment.this,
                    time.get(Calendar.HOUR_OF_DAY),
                    time.get(Calendar.MINUTE),
                    true);
            datePickerDialog.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        date.set(year, monthOfYear, dayOfMonth);
        setDate(DateFormat.getDateInstance(), date.getTime(), mDateTextView);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time.set(Calendar.HOUR_OF_DAY, hourOfDay);
        time.set(Calendar.MINUTE, minute);
        setDate(DateFormat.getTimeInstance(), time.getTime(), mTimeTextView);
    }

    public static Fragment newInstance() {
        return new ServiceDatesFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (time != null)
            outState.putSerializable("time", time);
        if (date != null)
            outState.putSerializable("date", date);
    }
}
