// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.quizrr.InfoActivies;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;
import quizrr.quizrr.com.R;

public class LosersActivity_ViewBinding implements Unbinder {
  private LosersActivity target;

  @UiThread
  public LosersActivity_ViewBinding(LosersActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LosersActivity_ViewBinding(LosersActivity target, View source) {
    this.target = target;

    target.images = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.firstimage, "field 'images'", CircleImageView.class), 
        Utils.findRequiredViewAsType(source, R.id.secondimage, "field 'images'", CircleImageView.class), 
        Utils.findRequiredViewAsType(source, R.id.thirdimage, "field 'images'", CircleImageView.class));
    target.imagetext = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.firstext, "field 'imagetext'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.secondtext, "field 'imagetext'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.thirdtext, "field 'imagetext'", TextView.class));
  }

  @Override
  @CallSuper
  public void unbind() {
    LosersActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.images = null;
    target.imagetext = null;
  }
}
