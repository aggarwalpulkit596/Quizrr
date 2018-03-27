package quizrr.quizrr.quizrr.Adapters;

import android.content.Context;
import android.content.Intent;
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

import quizrr.quizrr.quizrr.Activities.LeaderBoardActivity;
import quizrr.quizrr.quizrr.Activities.QuizReviewActivity;
import quizrr.quizrr.quizrr.MODELS.PastQuizes;
import quizrr.quizrr.com.R;

/**
 * Created by pulkit-mac on 20/03/18.
 */

public class PastQuizesAdapter extends ArrayAdapter<PastQuizes> {
    ArrayList<PastQuizes> list;
    Context context;


    public PastQuizesAdapter(@NonNull Context context, ArrayList<PastQuizes> list) {
        super(context, 0);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public PastQuizes getItem(int i) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.pastquizlayout, parent, false);

            TextView date = convertView.findViewById(R.id.datetextview);
            TextView topic = convertView.findViewById(R.id.topictextview);
            TextView leaderbtn = convertView.findViewById(R.id.leaderboardtextview);
            TextView quizbtn = convertView.findViewById(R.id.quizreviewtextview);
            QuizViewHolder quizViewHolder = new QuizViewHolder(date, topic, leaderbtn, quizbtn);
            convertView.setTag(quizViewHolder);
        }

        final PastQuizes e = getItem(position);
        QuizViewHolder quizViewHolder = (QuizViewHolder) convertView.getTag();
        assert e != null;
        quizViewHolder.datetextview.setText(formatDate(e.getDate()*1000));
        quizViewHolder.topictextview.setText(e.getTopic());
        quizViewHolder.leaderboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, QuizReviewActivity.class);
                i.putExtra("quizid", e.getId());
                i.putExtra("topic",e.getTopic());
                i.putExtra("noofquestions",e.getQuestionsno());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                context.startActivity(i);
            }
        });
        quizViewHolder.quizreviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, LeaderBoardActivity.class);
                i.putExtra("quizid", e.getId());
                i.putExtra("topic",e.getTopic());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                context.startActivity(i);
            }
        });
        return convertView;
    }

    private String formatDate(long milliseconds) /* This is your topStory.getTime()*1000 */ {
//        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        DateFormat sdf = new SimpleDateFormat("dd MMM");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(calendar.getTime());
    }
}