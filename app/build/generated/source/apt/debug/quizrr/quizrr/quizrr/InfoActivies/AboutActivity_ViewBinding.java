// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.quizrr.InfoActivies;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import quizrr.quizrr.com.R;

public class AboutActivity_ViewBinding implements Unbinder {
  private AboutActivity target;

  @UiThread
  public AboutActivity_ViewBinding(AboutActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutActivity_ViewBinding(AboutActivity target, View source) {
    this.target = target;

    target.aboutusterms = Utils.findRequiredViewAsType(source, R.id.aboutusterms, "field 'aboutusterms'", TextView.class);
    target.aboutuspolicy = Utils.findRequiredViewAsType(source, R.id.aboutuspolicy, "field 'aboutuspolicy'", TextView.class);
    target.Appversion = Utils.findRequiredViewAsType(source, R.id.appversiontext, "field 'Appversion'", TextView.class);
    target.texts = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.logoutbuttontext, "field 'texts'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.ratebuttontext, "field 'texts'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.rulesButtontext, "field 'texts'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.howtoplayButtontext, "field 'texts'", TextView.class));
    target.buttons = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.instabutton, "field 'buttons'", ImageButton.class), 
        Utils.findRequiredViewAsType(source, R.id.fbbutton, "field 'buttons'", ImageButton.class), 
        Utils.findRequiredViewAsType(source, R.id.twitterbutton, "field 'buttons'", ImageButton.class), 
        Utils.findRequiredViewAsType(source, R.id.rulesButton, "field 'buttons'", ImageButton.class), 
        Utils.findRequiredViewAsType(source, R.id.logoutbutton, "field 'buttons'", ImageButton.class), 
        Utils.findRequiredViewAsType(source, R.id.ratebutton, "field 'buttons'", ImageButton.class), 
        Utils.findRequiredViewAsType(source, R.id.howtoplayButton, "field 'buttons'", ImageButton.class));
  }

  @Override
  @CallSuper
  public void unbind() {
    AboutActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.aboutusterms = null;
    target.aboutuspolicy = null;
    target.Appversion = null;
    target.texts = null;
    target.buttons = null;
  }
}
