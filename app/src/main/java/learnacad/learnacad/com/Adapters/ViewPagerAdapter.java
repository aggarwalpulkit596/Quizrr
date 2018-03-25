package learnacad.learnacad.com.Adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import katex.hourglass.in.mathlib.MathView;
import learnacad.learnacad.com.Activities.QuizReviewActivity;
import learnacad.learnacad.com.MODELS.Instruction;
import learnacad.learnacad.com.MODELS.Quiz;
import learnacad.learnacad.com.MODELS.ReviewQuiz;
import learnacad.learnacad.com.QuizApp;
import learnacad.learnacad.com.R;
import learnacad.learnacad.com.REST.ApiClients;
import learnacad.learnacad.com.REST.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int value;
    ArrayList<Instruction> list;
    String quizid;


    public ViewPagerAdapter(Context mContext, String quizid) {
        this.mContext = mContext;
        this.quizid = quizid;

    }


    public ViewPagerAdapter(Context mContext, ArrayList<Instruction> instructionList, int value) {
        this.mContext = mContext;
        this.list = instructionList;
        this.value = value;
    }

    public ViewPagerAdapter(QuizReviewActivity mContext, String quizid, int noofquestions) {
        this.mContext = mContext;
        this.quizid = quizid;
        this.value = noofquestions;
    }

    @Override
    public int getCount() {
        int count;
        if (value == 1)
            count = list.size();
        else
            count = value;
        Log.i("TAG", "getCount: " + count);

        return count;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView;
        if (value == 1) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.instruction_content, container, false);
            TextView heading = itemView.findViewById(R.id.instructionheading);
            TextView instruction = itemView.findViewById(R.id.instructiontext);
            instruction.setText(list.get(position).getText());
            heading.setText(list.get(position).getHeader());
        } else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.quizlayout, container, false);
            fetchquizreview(position, quizid, itemView);
        }


        container.addView(itemView);
        return itemView;
    }

    private void fetchquizreview(final int position, String quizid, final View itemView) {
        ApiInterface apiService = ApiClients.getClient().create(ApiInterface.class);
        Call<ReviewQuiz> call = apiService.revquestion(QuizApp.getPreferenceManager().getString(MyPreferenceManager.KEY_ACCESS_TOKEN), quizid, position + 1);
        call.enqueue(new Callback<ReviewQuiz>() {
            @Override
            public void onResponse(Call<ReviewQuiz> call, Response<ReviewQuiz> response) {
                Log.i("TAG", "onResponse: " + response.body());
                ReviewQuiz rv = response.body();
                MathView question = itemView.findViewById(R.id.quizquestion);
                MathView option1 = itemView.findViewById(R.id.responseanswebview1);
                MathView option2 = itemView.findViewById(R.id.responseanswebview2);
                MathView option3 = itemView.findViewById(R.id.responseanswebview3);
                MathView option4 = itemView.findViewById(R.id.responseanswebview4);
                TextView questionnumber = itemView.findViewById(R.id.questionnumbertextview);
                TextView youranswer = itemView.findViewById(R.id.reviewyouanswertime);
                TextView leasttime = itemView.findViewById(R.id.reviewleasttime);
                RelativeLayout background1 = itemView.findViewById(R.id.responseansbackground1);
                RelativeLayout background2 = itemView.findViewById(R.id.responseansbackground2);
                RelativeLayout background3 = itemView.findViewById(R.id.responseansbackground3);
                RelativeLayout background4 = itemView.findViewById(R.id.responseansbackground4);
                ImageView optionA = itemView.findViewById(R.id.optionimageA);
                ImageView optionB = itemView.findViewById(R.id.optionimageB);
                ImageView optionC = itemView.findViewById(R.id.optionimageC);
                ImageView optionD = itemView.findViewById(R.id.optionimageD);
                TypedArray img, img2;
                img2 = mContext.getResources().obtainTypedArray(R.array.incorrectletter);
                img = mContext.getResources().obtainTypedArray(R.array.correctletter);


                question.setDisplayText(rv.getQuestionText());
                question.setTextSize(18);
                option1.setDisplayText(rv.getOptions().get(0).getValue());
                option1.setTextSize(15);
                option2.setDisplayText(rv.getOptions().get(1).getValue());
                option2.setTextSize(15);
                option3.setDisplayText(rv.getOptions().get(2).getValue());
                option3.setTextSize(15);
                option4.setDisplayText(rv.getOptions().get(3).getValue());
                option4.setTextSize(15);
                questionnumber.setText((position + 1) + "");
                leasttime.setText(rv.getLeastTime() + " sec");
                int ans = rv.getCorrectAnswer();
                if (ans == 1) {
                    optionA.setImageResource(img.getResourceId(ans - 1, 0));
                    option1.setTextColor(Color.parseColor("#40c057"));
                } else if (ans == 2) {
                    optionB.setImageResource(img.getResourceId(ans - 1, 0));
                    option2.setTextColor(Color.parseColor("#40c057"));
                } else if (ans == 3) {
                    optionC.setImageResource(img.getResourceId(ans - 1, 0));
                    option3.setTextColor(Color.parseColor("#40c057"));
                } else if (ans == 4) {
                    optionD.setImageResource(img.getResourceId(ans - 1, 0));
                    option4.setTextColor(Color.parseColor("#40c057"));
                }
                if (rv.getHasAttempted()) {
                    youranswer.setText(rv.getTimeTaken() + " sec");
                    if (rv.getIsCorrect() && ans == rv.getChosenAnswer()) {
                        if (ans == 1 && ans == rv.getChosenAnswer())
                            background1.setBackgroundColor(Color.parseColor("#e9e0f2"));
                        else if (ans == 2 && ans == rv.getChosenAnswer())
                            background2.setBackgroundColor(Color.parseColor("#e9e0f2"));
                        else if (ans == 3 && ans == rv.getChosenAnswer())
                            background3.setBackgroundColor(Color.parseColor("#e9e0f2"));
                        else if (ans == 4 && ans == rv.getChosenAnswer())
                            background4.setBackgroundColor(Color.parseColor("#e9e0f2"));
                    } else {
                        ans = rv.getChosenAnswer();
                        if (ans == 1) {
                            optionA.setImageResource(img2.getResourceId(ans - 1, 0));
                            background1.setBackgroundColor(Color.parseColor("#e9e0f2"));
                            option1.setTextColor(Color.parseColor("#d63f3a"));
                        } else if (ans == 2) {
                            background2.setBackgroundColor(Color.parseColor("#e9e0f2"));
                            optionB.setImageResource(img2.getResourceId(ans - 1, 0));
                            option2.setTextColor(Color.parseColor("#d63f3a"));
                        } else if (ans == 3) {
                            background3.setBackgroundColor(Color.parseColor("#e9e0f2"));
                            optionC.setImageResource(img2.getResourceId(ans - 1, 0));
                            option3.setTextColor(Color.parseColor("#d63f3a"));
                        } else if (ans == 4) {
                            background4.setBackgroundColor(Color.parseColor("#e9e0f2"));
                            optionD.setImageResource(img2.getResourceId(ans - 1, 0));
                            option4.setTextColor(Color.parseColor("#d63f3a"));
                        }

                    }
                } else

                {
                    youranswer.setText("-");
                }


            }

            @Override
            public void onFailure(Call<ReviewQuiz> call, Throwable t) {

            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (value == 1) {
            container.removeView((FrameLayout) object);
        } else
            container.removeView((LinearLayout) object);
    }
}
