package com.example.faculdade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[][] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView1;
        public TextView textView2;
        public ImageView imageView;

        public MyViewHolder(View v) {
            super(v);

            textView1 = v.findViewById(R.id.my_text_view1);

            textView2 = v.findViewById(R.id.my_text_view2);

            imageView = v.findViewById(R.id.imageView);
        }
    }

    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[][] myDataset, Context context_) {
        mDataset = myDataset;
        this.context = context_;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line, parent, false);
        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView1.setText(mDataset[position][0]);
        holder.textView2.setText(mDataset[position][1]);
    }

    //Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}