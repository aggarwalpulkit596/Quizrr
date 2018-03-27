// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.com;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.captain_miao.optroundcardview.OptRoundCardView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import java.lang.IllegalStateException;
import java.lang.Override;
import katex.hourglass.in.mathlib.MathView;
import quizrr.quizrr.quizrr.Game2Activity;

public class Game2Activity_ViewBinding implements Unbinder {
  private Game2Activity target;

  @UiThread
  public Game2Activity_ViewBinding(Game2Activity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Game2Activity_ViewBinding(Game2Activity target, View source) {
    this.target = target;

    target.transitionsContainer = Utils.findRequiredViewAsType(source, R.id.transitions_container, "field 'transitionsContainer'", LinearLayout.class);
    target.questionlayout = Utils.findRequiredViewAsType(source, R.id.questionactivity, "field 'questionlayout'", RelativeLayout.class);
    target.questiontimespent = Utils.findRequiredViewAsType(source, R.id.questiontimespentvalue, "field 'questiontimespent'", TextView.class);
    target.questionnofplayers = Utils.findRequiredViewAsType(source, R.id.questionnoofplayerstextview, "field 'questionnofplayers'", TextView.class);
    target.questionnumbertextview = Utils.findRequiredViewAsType(source, R.id.questionnumbertextview, "field 'questionnumbertextview'", TextView.class);
    target.questionprogressbar = Utils.findRequiredViewAsType(source, R.id.questionprogressbar, "field 'questionprogressbar'", ProgressBar.class);
    target.gamequestiontext = Utils.findRequiredViewAsType(source, R.id.questionwebview1, "field 'gamequestiontext'", MathView.class);
    target.infobutton = Utils.findRequiredViewAsType(source, R.id.gameinfobtn, "field 'infobutton'", ImageView.class);
    target.questionmode = Utils.findRequiredViewAsType(source, R.id.questionmode, "field 'questionmode'", LinearLayout.class);
    target.giflayout = Utils.findRequiredViewAsType(source, R.id.gifactivtity, "field 'giflayout'", RelativeLayout.class);
    target.gifImageView = Utils.findRequiredViewAsType(source, R.id.gifimage, "field 'gifImageView'", SimpleExoPlayerView.class);
    target.giftext = Utils.findRequiredViewAsType(source, R.id.gifttext, "field 'giftext'", TextView.class);
    target.latelayout = Utils.findRequiredViewAsType(source, R.id.latelayout, "field 'latelayout'", RelativeLayout.class);
    target.latenextquestiontime = Utils.findRequiredViewAsType(source, R.id.latenextquestiontimer, "field 'latenextquestiontime'", TextView.class);
    target.latenoofplayer = Utils.findRequiredViewAsType(source, R.id.latenoofplayerstextview, "field 'latenoofplayer'", TextView.class);
    target.responselayout = Utils.findRequiredViewAsType(source, R.id.responseactivity, "field 'responselayout'", RelativeLayout.class);
    target.responsequestiontimer = Utils.findRequiredViewAsType(source, R.id.responsenextquestiontimer, "field 'responsequestiontimer'", TextView.class);
    target.responsenoofplayers = Utils.findRequiredViewAsType(source, R.id.responsenoofplayerstextview, "field 'responsenoofplayers'", TextView.class);
    target.responsequestimertextview = Utils.findRequiredViewAsType(source, R.id.responsenextquestiontimertextview, "field 'responsequestimertextview'", TextView.class);
    target.responseimageview = Utils.findRequiredViewAsType(source, R.id.responseimageview, "field 'responseimageview'", ImageView.class);
    target.gameresponsemessage = Utils.findRequiredViewAsType(source, R.id.responsemesaagetextview, "field 'gameresponsemessage'", TextView.class);
    target.gameresponsetitle = Utils.findRequiredViewAsType(source, R.id.responsetitletextview, "field 'gameresponsetitle'", TextView.class);
    target.gameyouranswertime = Utils.findRequiredViewAsType(source, R.id.responseyouranswertime, "field 'gameyouranswertime'", TextView.class);
    target.gamequickanswer = Utils.findRequiredViewAsType(source, R.id.responsequickestanswer, "field 'gamequickanswer'", TextView.class);
    target.heartimage = Utils.findRequiredViewAsType(source, R.id.responseheartimage, "field 'heartimage'", ImageView.class);
    target.questionoptionstext = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.questionanswebview1, "field 'questionoptionstext'", MathView.class), 
        Utils.findRequiredViewAsType(source, R.id.questionanswebview2, "field 'questionoptionstext'", MathView.class), 
        Utils.findRequiredViewAsType(source, R.id.questionanswebview3, "field 'questionoptionstext'", MathView.class), 
        Utils.findRequiredViewAsType(source, R.id.questionanswebview4, "field 'questionoptionstext'", MathView.class));
    target.questionbackground = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.questioncardView2, "field 'questionbackground'", OptRoundCardView.class), 
        Utils.findRequiredViewAsType(source, R.id.questioncardView3, "field 'questionbackground'", OptRoundCardView.class), 
        Utils.findRequiredViewAsType(source, R.id.questioncardView4, "field 'questionbackground'", OptRoundCardView.class), 
        Utils.findRequiredViewAsType(source, R.id.questioncardView5, "field 'questionbackground'", OptRoundCardView.class));
    target.optionletter = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.optionimageA, "field 'optionletter'", ImageView.class), 
        Utils.findRequiredViewAsType(source, R.id.optionimageB, "field 'optionletter'", ImageView.class), 
        Utils.findRequiredViewAsType(source, R.id.optionimageC, "field 'optionletter'", ImageView.class), 
        Utils.findRequiredViewAsType(source, R.id.optionimageD, "field 'optionletter'", ImageView.class));
    target.responseprogressview = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.responseansprogress1, "field 'responseprogressview'", ProgressBar.class), 
        Utils.findRequiredViewAsType(source, R.id.responseansprogress2, "field 'responseprogressview'", ProgressBar.class), 
        Utils.findRequiredViewAsType(source, R.id.responseansprogress3, "field 'responseprogressview'", ProgressBar.class), 
        Utils.findRequiredViewAsType(source, R.id.responseansprogress4, "field 'responseprogressview'", ProgressBar.class));
    target.responseansbackground = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.responseansbackground1, "field 'responseansbackground'", RelativeLayout.class), 
        Utils.findRequiredViewAsType(source, R.id.responseansbackground2, "field 'responseansbackground'", RelativeLayout.class), 
        Utils.findRequiredViewAsType(source, R.id.responseansbackground3, "field 'responseansbackground'", RelativeLayout.class), 
        Utils.findRequiredViewAsType(source, R.id.responseansbackground4, "field 'responseansbackground'", RelativeLayout.class));
    target.responsebackground = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.responsecardview2, "field 'responsebackground'", OptRoundCardView.class), 
        Utils.findRequiredViewAsType(source, R.id.responsecardview3, "field 'responsebackground'", OptRoundCardView.class), 
        Utils.findRequiredViewAsType(source, R.id.responsecardview4, "field 'responsebackground'", OptRoundCardView.class), 
        Utils.findRequiredViewAsType(source, R.id.responsecardview5, "field 'responsebackground'", OptRoundCardView.class));
    target.responsetextview = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.responseanswebview1, "field 'responsetextview'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.responseanswebview2, "field 'responsetextview'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.responseanswebview3, "field 'responsetextview'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.responseanswebview4, "field 'responsetextview'", TextView.class));
    target.responseletter = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.responseimageA, "field 'responseletter'", ImageView.class), 
        Utils.findRequiredViewAsType(source, R.id.responseimageB, "field 'responseletter'", ImageView.class), 
        Utils.findRequiredViewAsType(source, R.id.responseimageC, "field 'responseletter'", ImageView.class), 
        Utils.findRequiredViewAsType(source, R.id.responseimageD, "field 'responseletter'", ImageView.class));
  }

  @Override
  @CallSuper
  public void unbind() {
    Game2Activity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.transitionsContainer = null;
    target.questionlayout = null;
    target.questiontimespent = null;
    target.questionnofplayers = null;
    target.questionnumbertextview = null;
    target.questionprogressbar = null;
    target.gamequestiontext = null;
    target.infobutton = null;
    target.questionmode = null;
    target.giflayout = null;
    target.gifImageView = null;
    target.giftext = null;
    target.latelayout = null;
    target.latenextquestiontime = null;
    target.latenoofplayer = null;
    target.responselayout = null;
    target.responsequestiontimer = null;
    target.responsenoofplayers = null;
    target.responsequestimertextview = null;
    target.responseimageview = null;
    target.gameresponsemessage = null;
    target.gameresponsetitle = null;
    target.gameyouranswertime = null;
    target.gamequickanswer = null;
    target.heartimage = null;
    target.questionoptionstext = null;
    target.questionbackground = null;
    target.optionletter = null;
    target.responseprogressview = null;
    target.responseansbackground = null;
    target.responsebackground = null;
    target.responsetextview = null;
    target.responseletter = null;
  }
}
