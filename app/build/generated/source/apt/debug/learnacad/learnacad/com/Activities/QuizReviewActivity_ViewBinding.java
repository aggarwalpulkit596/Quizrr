// Generated code from Butter Knife. Do not modify!
package learnacad.learnacad.com.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import learnacad.learnacad.com.R;

public class QuizReviewActivity_ViewBinding implements Unbinder {
  private QuizReviewActivity target;

  @UiThread
  public QuizReviewActivity_ViewBinding(QuizReviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QuizReviewActivity_ViewBinding(QuizReviewActivity target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewPager'", ViewPager.class);
    target.pager_indicator = Utils.findRequiredViewAsType(source, R.id.viewPagerCountDots, "field 'pager_indicator'", LinearLayout.class);
    target.prevbtn = Utils.findRequiredViewAsType(source, R.id.prevbtn, "field 'prevbtn'", TextView.class);
    target.nextbtn = Utils.findRequiredViewAsType(source, R.id.nextbtn, "field 'nextbtn'", TextView.class);
    target.topictextview = Utils.findRequiredViewAsType(source, R.id.topictextview, "field 'topictextview'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QuizReviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
    target.pager_indicator = null;
    target.prevbtn = null;
    target.nextbtn = null;
    target.topictextview = null;
  }
}
