package eparon.npsbrowserj.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import eparon.npsbrowserj.R;
import eparon.npsbrowserj.TSV.BaseTSV;
import eparon.npsbrowserj.TSV.VitaGames;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private ArrayList<BaseTSV> appsList;
    private Context mContext;

    public AppAdapter (ArrayList<BaseTSV> appsList, Context context) {
        this.appsList = appsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public AppAdapter.ViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int viewType) {
        return new AppAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder (@NonNull AppAdapter.ViewHolder holder, int position) {
        BaseTSV currentItem = appsList.get(position);

        holder.appTitle.setText(currentItem.getTitle());
        holder.appRegion.setText(currentItem.getRegion());
        if (currentItem instanceof VitaGames) holder.appMinFW.setText(((VitaGames)currentItem).getMinFW());
        holder.appMinFW.setBackground(null);

        if (!holder.appMinFW.getText().equals("")) {
            Drawable drawable = ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.rect_red, null);
            holder.appMinFW.setBackground(drawable);
        }

        holder.appLastDate.setText(currentItem.getLastDateTime());
    }

    @Override
    public int getItemCount () {
        return appsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView appTitle, appRegion, appMinFW, appLastDate;

        public ViewHolder (View itemView) {
            super(itemView);
            appTitle = itemView.findViewById(R.id.appTitle);
            appRegion = itemView.findViewById(R.id.appRegion);
            appMinFW = itemView.findViewById(R.id.appMinFW);
            appLastDate = itemView.findViewById(R.id.appLastDate);
        }
    }

}
