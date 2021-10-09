package com.example.crewmembers.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crewmembers.CrewMember;
import com.example.crewmembers.CrewMemberDatabase;
import com.example.crewmembers.MainActivity;
import com.example.crewmembers.R;
import com.example.crewmembers.ViewModel.CourseModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    // creating a variable for array list and context.
    //private ArrayList<CourseModal> courseModalArrayList;
    private List<CrewMember> crewMemberList;
    private Context context;

    // creating a constructor for our variables.
  //  public CourseAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
  //      this.courseModalArrayList = courseModalArrayList;
  //      this.context = context;
  //  }

    public CourseAdapter(List<CrewMember> crewMemberList, Context context) {
        this.crewMemberList = crewMemberList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        //CourseModal modal = courseModalArrayList.get(position);
        CrewMember crewMember = crewMemberList.get(position);
        holder.name.setText(crewMember.getName());
        holder.agency.setText(crewMember.getAgency());
        holder.status.setText(crewMember.getStatus());
        String url = crewMember.getWiki();
        holder.wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW
                        , Uri.parse(url));

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
        holder.wiki.setText(crewMember.getWiki());
        holder.wiki.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.wiki.setTextColor(R.color.purple_500);
        Picasso.get().load(crewMember.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return crewMemberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView name, wiki, agency, status;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            name = itemView.findViewById(R.id.name);
            wiki = itemView.findViewById(R.id.wiki);
            agency = itemView.findViewById(R.id.agency);
            status = itemView.findViewById(R.id.status);
            image = itemView.findViewById(R.id.image);
        }
    }
}
