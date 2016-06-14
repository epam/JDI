package com.epam.jdi.jditestandroidapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epam.jdi.jditestandroidapp.ListItem;
import com.epam.jdi.jditestandroidapp.R;
import com.epam.jdi.jditestandroidapp.adapter.MultiListAdapter;
import com.epam.jdi.jditestandroidapp.base.BackFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitalii_sokolov on 08.06.16.
 */
public class ListFragment extends BackFragment {

    private static final String ORIENTATION = "orientation";
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    public static final int GRID = 3;
    private static final String ITEM_TYPE = "item_type";
    public static final int CARD = 2;
    public static final int ITEM = 1;
    public static List<ListItem> list = new ArrayList<>();

    static {
        list.add(new ListItem(R.string.one, false));
        list.add(new ListItem(R.string.two, false));
        list.add(new ListItem(R.string.three, false));
        list.add(new ListItem(R.string.four, false));
        list.add(new ListItem(R.string.five, false));
        list.add(new ListItem(R.string.six, false));
        list.add(new ListItem(R.string.seven, false));
        list.add(new ListItem(R.string.eight, false));
        list.add(new ListItem(R.string.nine, false));
        list.add(new ListItem(R.string.ten, false));
    }

    RecyclerView mList;
    private Toast toast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = (RecyclerView) view.findViewById(android.R.id.list);

        int orientation = getArguments().getInt(ORIENTATION);

        switch (orientation) {
            case HORIZONTAL:
                mList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                break;
            case VERTICAL:
                mList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                break;
            case GRID:
                mList.setLayoutManager(new GridLayoutManager(getContext(), 2));
                break;
        }

        int itemType = getArguments().getInt(ITEM_TYPE);
        MultiListAdapter multiListAdapter = new MultiListAdapter(getContext(), list, itemType);
        multiListAdapter.setOnItemClickListener(new MultiListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, long id, int position) {
                if(toast !=null) toast.cancel();
                toast = Toast.makeText(getContext(), "Click on " + (position + 1) + " item", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        mList.setAdapter(multiListAdapter);

    }

    public static Fragment newInstance(int item) {

        Bundle b = new Bundle();
        b.putInt(ITEM_TYPE, item);
        return newInstance(b);
    }

    public static Fragment newInstance(int item, int orientation) {

        Bundle b = new Bundle();
        b.putInt(ITEM_TYPE, item);
        if (orientation > 0) {
            b.putInt(ORIENTATION, orientation);
        }
        return newInstance(b);
    }

    private static Fragment newInstance(Bundle b) {
        ListFragment fragment = new ListFragment();
        fragment.setArguments(b);
        return fragment;
    }

}
