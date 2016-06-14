package com.epam.jdi.jditestandroidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.epam.jdi.jditestandroidapp.ListItem;
import com.epam.jdi.jditestandroidapp.R;
import com.epam.jdi.jditestandroidapp.ui.ListFragment;

import java.util.List;

/**
 * Created by vitalii_sokolov on 08.06.16.
 */
public class MultiListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private final int mItemType;
    private Context mContext;
    OnItemClickListener mOnItemClickListener;
    private List<ListItem> items;

    public MultiListAdapter(Context context, List<ListItem> listItems, int itemType) {
        this.mItemType = itemType;
        mContext = context;
        items = listItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        if (mItemType == ListFragment.ITEM) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            viewHolder = new ItemViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.card_view, parent, false);
            viewHolder = new CardViewHolder(view);
        }
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).bind(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            final RecyclerView recyclerView = (RecyclerView) view.getParent();
            final int position = recyclerView.getChildLayoutPosition(view);
            if (position != RecyclerView.NO_POSITION) {
                mOnItemClickListener.onItemClick(view, getItemId(position), position);
            }
        }
    }


    abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        abstract void bind(ListItem item);

    }

    class ItemViewHolder extends BaseViewHolder {
        TextView mtext;
        CheckBox mCheckBox;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mtext = (TextView) itemView.findViewById(android.R.id.text1);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

        @Override
        void bind(final ListItem item) {

            mtext.setText(mContext.getString(item.text));
            mCheckBox.setChecked(item.checked);
            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.checked = isChecked;
                }
            });

        }
    }

    class CardViewHolder extends BaseViewHolder {
        TextView mText;
        ToggleButton mToggleBtn;

        public CardViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(android.R.id.text1);
            mToggleBtn = (ToggleButton) itemView.findViewById(R.id.toggleButton);
        }

        @Override
        void bind(final ListItem item) {
            mText.setText(mContext.getString(item.text));
            mToggleBtn.setChecked(item.checked);
            mToggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.checked = isChecked;
                }
            });

        }
    }
public void setOnItemClickListener(OnItemClickListener listener){
    this.mOnItemClickListener = listener;
}
    public interface OnItemClickListener {
        void onItemClick(View view, long id, int position);
    }
}
