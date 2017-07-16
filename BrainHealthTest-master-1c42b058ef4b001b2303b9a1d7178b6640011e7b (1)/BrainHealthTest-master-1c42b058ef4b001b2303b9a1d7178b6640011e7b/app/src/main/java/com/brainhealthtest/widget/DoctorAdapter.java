package com.brainhealthtest.widget;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brainhealthtest.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import co.dift.ui.SwipeToAction;

/**
 * Created by whaodar on 2016/12/9.
 */

public class DoctorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<TestCase> items;


    /**
     * References to the views for each data item
     **/
    public class CaseViewHolder extends SwipeToAction.ViewHolder<TestCase>
    {
        public TextView titleView;
        public TextView authorView;
        public SimpleDraweeView imageView;

        public CaseViewHolder(View v)
        {
            super(v);
            titleView = (TextView) v.findViewById(R.id.title);
            authorView = (TextView) v.findViewById(R.id.author);
            imageView = (SimpleDraweeView) v.findViewById(R.id.image);
        }
    }

    /**
     * Constructor
     **/
    public DoctorAdapter(List<TestCase> items)
    {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doctor_case, parent, false);

        return new CaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        TestCase item = items.get(position);
        CaseViewHolder vh = (CaseViewHolder) holder;
        vh.titleView.setText(item.getTitle());
        vh.authorView.setText(item.getAuthor());
        vh.imageView.setImageURI(Uri.parse(item.getImageUrl()));
        vh.data = item;
    }
}