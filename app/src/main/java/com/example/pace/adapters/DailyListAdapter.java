package com.example.pace.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pace.R;
import com.example.pace.clientuser.ClientDataDailyList;
import com.example.pace.config.ListHolder;
import com.example.pace.graphutil.graphutilfragments.DailyListItemFragment;

public class DailyListAdapter extends RecyclerView.Adapter<DailyListAdapter.ViewHolder> {

    private Context applicationContext;
    public DailyListAdapter(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @NonNull
    @Override
    public DailyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyListAdapter.ViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(this.applicationContext, R.anim.recycler_view_animation_1);
        holder.itemView.startAnimation(animation);

        holder.dateText.setText(determineDateFormat(ListHolder.getInstance().outputDailyDataList.get(position)));
        ListHolder.getInstance().outputDailyDataList.get(position).setFormattedDate(determineDateFormat(ListHolder.getInstance().outputDailyDataList.get(position)));
        holder.routesText.setText("Routes: ");
        holder.routesNum.setText(""+ListHolder.getInstance().outputDailyDataList.get(position).getClientDataList().size());
        holder.expenditureText.setText("$ "+String.format("%.2f",ListHolder.getInstance().outputDailyDataList.get(position).getExpenditure()));
        holder.relatedPercentage.setText("% "+String.format("%.2f",ListHolder.getInstance().outputDailyDataList.get(position).getPercentageDifference()));
    }

    @Override
    public int getItemCount() {
        return ListHolder.getInstance().outputDailyDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateText;
        public TextView routesText;
        public TextView routesNum;
        public TextView expenditureText;
        public TextView relatedPercentage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dateText = itemView.findViewById(R.id.layout_list_dateText);
            this.routesText = itemView.findViewById(R.id.layout_list_routesText);
            this.routesNum = itemView.findViewById(R.id.layout_list_routesNum);
            this.expenditureText = itemView.findViewById(R.id.layout_list_expenditureText);
            this.relatedPercentage = itemView.findViewById(R.id.layout_list_expenditurePercentageText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListHolder.getInstance().outputDailyDataListIndex = getAdapterPosition();
                    FragmentTransaction fragmentTransaction = ListHolder.getInstance().fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.activity_main_mainFrame, new DailyListItemFragment());
                    fragmentTransaction.commit();
                }
            });
        }
    }

    public String determineDateFormat(ClientDataDailyList clientData) {
        String[] monthArr = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        return monthArr[clientData.getMonth() - 1] + " " + clientData.getDay() + ", " + clientData.getYear();
    }
}
