// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.com.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

import quizrr.quizrr.quizrr.Activities.InstructionActivity;
import quizrr.quizrr.quizrr.Adapters.ViewPagerCustomDuration;
import quizrr.quizrr.com.R;

public class InstructionActivity_ViewBinding implements Unbinder {
  private InstructionActivity target;

  @UiThread
  public InstructionActivity_ViewBinding(InstructionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InstructionActivity_ViewBinding(InstructionActivity target, View source) {
    this.target = target;

    target.timertextview = Utils.findRequiredViewAsType(source, R.id.timertextview, "field 'timertextview'", TextView.class);
    target.studentstextview = Utils.findRequiredViewAsType(source, R.id.noofstudentstextview, "field 'studentstextview'", TextView.class);
    target.final10seconds = Utils.findRequiredViewAsType(source, R.id.lessthan10seconds, "field 'final10seconds'", TextView.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.progressBarcircle, "field 'relativeLayout'", RelativeLayout.class);
    target.gamestarttextview = Utils.findRequiredViewAsType(source, R.id.gamestarttextview, "field 'gamestarttextview'", TextView.class);
    target.mPager = Utils.findRequiredViewAsType(source, R.id.pager, "field 'mPager'", ViewPagerCustomDuration.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InstructionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.timertextview = null;
    target.studentstextview = null;
    target.final10seconds = null;
    target.relativeLayout = null;
    target.gamestarttextview = null;
    target.mPager = null;
  }
}
