package com.epam.jdi.jditestandroidapp.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.epam.jdi.jditestandroidapp.MainItem;
import com.epam.jdi.jditestandroidapp.R;
import com.epam.jdi.jditestandroidapp.adapter.ItemAdapter;
import com.epam.jdi.jditestandroidapp.base.BackFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BackFragment implements AdapterView.OnItemClickListener {

    ListView mList;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<MainItem> list = new ArrayList<>();
        list.add(new MainItem(R.string.menu_contact_form, R.id.contact_form));
        list.add(new MainItem(R.string.date_form, R.id.date_form));
        list.add(new MainItem(R.string.picker_form, R.id.picker_form));
        list.add(new MainItem(R.string.list_form, R.id.list_form));
        list.add(new MainItem(R.string.card_form, R.id.card_form));
        list.add(new MainItem(R.string.grid_form, R.id.grid_form));
        list.add(new MainItem(R.string.swipe_form, R.id.swipe_collection));


        mList = (ListView) view.findViewById(android.R.id.list);

        mList.setAdapter(new ItemAdapter(getContext(), list));
        mList.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        if (id == R.id.contact_form) {
            ft.replace(R.id.conatiner, ContactFormFragment.newInstance());
        } else if (id == R.id.date_form) {
            ft.replace(R.id.conatiner, ServiceDatesFragment.newInstance());
        } else if (id == R.id.picker_form) {
            ft.replace(R.id.conatiner, ServicePickerFragment.newInsance());
        } else if (id == R.id.list_form) {
            ft.replace(R.id.conatiner, ListFragment.newInstance(ListFragment.ITEM, ListFragment.VERTICAL));
        } else if (id == R.id.card_form) {
            ft.replace(R.id.conatiner, ListFragment.newInstance(ListFragment.CARD, ListFragment.VERTICAL));
        } else if (id == R.id.grid_form) {
            ft.replace(R.id.conatiner, ListFragment.newInstance(ListFragment.CARD, ListFragment.GRID));
        } else if (id == R.id.card_horiziontal) {
            ft.replace(R.id.conatiner, ListFragment.newInstance(ListFragment.CARD, ListFragment.HORIZONTAL));
        }else if(id == R.id.swipe_collection){
            ft.replace(R.id.conatiner,TabFragment.newInstance());
        }

        ft.commit();
    }

    public static Fragment newInstance() {
        return new MainFragment();
    }
}
