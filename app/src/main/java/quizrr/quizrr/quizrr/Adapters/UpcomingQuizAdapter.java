package quizrr.quizrr.quizrr.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import quizrr.quizrr.quizrr.MODELS.Quiz;
import quizrr.quizrr.com.R;

/**
 * Created by pulkit-mac on 20/03/18.
 */

public class UpcomingQuizAdapter extends ArrayAdapter<Quiz> {
    ArrayList<Quiz> list;
    Context context;


    public UpcomingQuizAdapter(@NonNull Context context, ArrayList<Quiz> list) {
        super(context, 0);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Quiz getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class QuizViewHolder {

        TextView datetextview;
        TextView topictextview;
        TextView leaderboardbtn;
        TextView quizreviewbtn;


        QuizViewHolder(TextView datetextview, TextView topictextview, TextView leaderboardbtn, TextView quizreviewbtn) {
            this.datetextview = datetextview;
            this.topictextview = topictextview;
            this.leaderboardbtn = leaderboardbtn;
            this.quizreviewbtn = quizreviewbtn;
        }

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.upcomingquizlayout, parent, false);

            TextView date = convertView.findViewById(R.id.datetextview);
            TextView topic = convertView.findViewById(R.id.topictextview);
            TextView leaderbtn = convertView.findViewById(R.id.leaderboardtextview);
            TextView quizbtn = convertView.findViewById(R.id.quizreviewtextview);
            QuizViewHolder quizViewHolder = new QuizViewHolder(date, topic, leaderbtn, quizbtn);
            convertView.setTag(quizViewHolder);
        }

        final Quiz e = getItem(position);
        QuizViewHolder quizViewHolder = (QuizViewHolder) convertView.getTag();
        assert e != null;
        quizViewHolder.datetextview.setText(formatDate(e.getDate() * 1000));
        quizViewHolder.topictextview.setText(e.getTopic());
        quizViewHolder.quizreviewbtn.setText("₹" + e.getPrize().get(0).getPrize());
        quizViewHolder.leaderboardbtn.setText("At " + formatDate2(e.getDate() * 1000));

        return convertView;
    }

    private String formatDate2(long milliseconds) {
        DateFormat sdf = new SimpleDateFormat("hh:mm aaa");
//        DateFormat sdf = new SimpleDateFormat("dd MMM");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(calendar.getTime());
    }

    private String formatDate(long milliseconds) /* This is your topStory.getTime()*1000 */ {
//        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        DateFormat sdf = new SimpleDateFormat("dd MMM ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(calendar.getTime());
    }
}