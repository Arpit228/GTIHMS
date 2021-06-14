package com.example.gtihms.authority;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gtihms.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class AdapterRoom extends FirebaseRecyclerAdapter<ModelRoom, AdapterRoom.ViewHolderRoom>{

    public AdapterRoom(@NonNull FirebaseRecyclerOptions<ModelRoom> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderRoom holder, int position, @NonNull ModelRoom model) {
        holder.roomno.setText(model.getRoomNo());
        holder.status.setText(model.getStatus());
    }

    @NonNull
    @Override
    public ViewHolderRoom onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roomrow, parent, false);
        return new ViewHolderRoom(view);
    }

    static class ViewHolderRoom extends RecyclerView.ViewHolder{

        TextView roomno, status;
        public ViewHolderRoom(@NonNull View itemView) {
            super(itemView);
            roomno = itemView.findViewById(R.id.roomnodata);
            status = itemView.findViewById(R.id.roomstatusdata);
        }
    }
}
