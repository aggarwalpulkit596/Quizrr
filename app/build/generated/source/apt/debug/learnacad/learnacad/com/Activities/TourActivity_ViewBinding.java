// Generated code from Butter Knife. Do not modify!
package learnacad.learnacad.com.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import learnacad.learnacad.com.Adapters.ViewPagerCustomDuration;
import learnacad.learnacad.com.R;

public class TourActivity_ViewBinding implements Unbinder {
  private TourActivity target;

  @UiThread
  public TourActivity_ViewBinding(TourActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TourActivity_ViewBinding(TourActivity target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewPager'", ViewPagerCustomDuration.class);
    target.pager_indicator = Utils.findRequiredViewAsType(source, R.id.viewPagerCountDots, "field 'pager_indicator'", LinearLayout.class);
    target.nextButton = Utils.findRequiredViewAsType(source, R.id.nextbutton, "field 'nextButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TourActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
    target.pager_indicator = null;
    target.nextButton = null;
  }
}
