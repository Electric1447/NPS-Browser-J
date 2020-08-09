package eparon.npsbrowserj.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import eparon.npsbrowserj.R;
import eparon.npsbrowserj.TSV.BaseTSV;
import eparon.npsbrowserj.TSV.VitaGames;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<BaseTSV> mAppsList;
    private ArrayList<BaseTSV> mAppsListFull;

    public AppAdapter (Context context, ArrayList<BaseTSV> appsList) {
        this.mContext = context;
        this.mAppsList = appsList;
        this.mAppsListFull = new ArrayList<>(mAppsList);
    }

    @NonNull
    @Override
    public AppAdapter.ViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int viewType) {
        return new AppAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder (@NonNull AppAdapter.ViewHolder holder, int position) {
        BaseTSV currentItem = mAppsList.get(position);

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
        return mAppsList.size();
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

    //region Filter

    public Filter getFilter () {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering (CharSequence constraint) {
            ArrayList<BaseTSV> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mAppsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (BaseTSV item : mAppsListFull)
                    if (item.getTitle().toLowerCase().contains(filterPattern))
                        filteredList.add(item);
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults (CharSequence constraint, FilterResults results) {
            mAppsList.clear();
            //noinspection unchecked
            mAppsList.addAll((ArrayList<BaseTSV>)results.values);
            notifyDataSetChanged();
        }
    };

    //endregion

}
