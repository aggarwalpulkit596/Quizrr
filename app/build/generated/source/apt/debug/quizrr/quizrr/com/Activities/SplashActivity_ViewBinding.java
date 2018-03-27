// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.com.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import quizrr.quizrr.com.R;
import quizrr.quizrr.quizrr.Activities.SplashActivity;

public class SplashActivity_ViewBinding implements Unbinder {
  private SplashActivity target;

  @UiThread
  public SplashActivity_ViewBinding(SplashActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashActivity_ViewBinding(SplashActivity target, View source) {
    this.target = target;

    target.splashLinearLayout = Utils.findRequiredViewAsType(source, R.id.splashlinearlayout, "field 'splashLinearLayout'", LinearLayout.class);
    target.splashrelativeLayout = Utils.findRequiredViewAsType(source, R.id.transitions_container, "field 'splashrelativeLayout'", RelativeLayout.class);
    target.splashimage = Utils.findRequiredViewAsType(source, R.id.splashimage, "field 'splashimage'", RelativeLayout.class);
    target.splashrulestextview = Utils.findRequiredViewAsType(source, R.id.splashrulestextview, "field 'splashrulestextview'", TextView.class);
    target.splashpolicytextview = Utils.findRequiredViewAsType(source, R.id.splashpolicytextview, "field 'splashpolicytextview'", TextView.class);
    target.splashtermstextview = Utils.findRequiredViewAsType(source, R.id.splashtermstextview, "field 'splashtermstextview'", TextView.class);
    target.splashgetstarted = Utils.findRequiredViewAsType(source, R.id.splashgetstarted, "field 'splashgetstarted'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SplashActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.splashLinearLayout = null;
    target.splashrelativeLayout = null;
    target.splashimage = null;
    target.splashrulestextview = null;
    target.splashpolicytextview = null;
    target.splashtermstextview = null;
    target.splashgetstarted = null;
  }
}
