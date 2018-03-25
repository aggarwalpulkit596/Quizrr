package learnacad.learnacad.com.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import learnacad.learnacad.com.MODELS.Leaderboard;
import learnacad.learnacad.com.MODELS.Winner;
import learnacad.learnacad.com.R;

/**
 * Created by pulkit-mac on 04/03/18.
 */

public class Leaderboardlistadapter extends ArrayAdapter<Winner> {
    ArrayList<Winner> list;
    Context context;


    public Leaderboardlistadapter(@NonNull Context context, ArrayList<Winner> list) {
        super(context, 0);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Winner getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class LeaderViewHolder {

        TextView posTextView;
        TextView nameTextView;
        TextView timeTextView;


        LeaderViewHolder(TextView posTextView, TextView nameTextView, TextView timeTextView) {
            this.posTextView = posTextView;
            this.nameTextView = nameTextView;
            this.timeTextView = timeTextView;
        }

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listlayout1, parent, false);

            TextView pos = convertView.findViewById(R.id.positiontextvieww);
            TextView name = convertView.findViewById(R.id.nametextview);
            TextView time = convertView.findViewById(R.id.timetextview);
            LeaderViewHolder leaderViewHolder = new LeaderViewHolder(pos, name, time);
            convertView.setTag(leaderViewHolder);
        }

        Winner e = getItem(position);//to differentiate between filtered list and todo list
        LeaderViewHolder leaderViewHolder = (LeaderViewHolder) convertView.getTag();
        assert e != null;
        leaderViewHolder.posTextView.setText(position + 1 + "");
        leaderViewHolder.nameTextView.setText(e.getUserName());
//        int min = e.getTotalTimeTaken() / 60;
//        int sec = e.getTotalTimeTaken() % 60;
        leaderViewHolder.timeTextView.setText(e.getTotalTimeTaken()+"");
        return convertView;
    }
}
