// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.quizrr.InfoActivies;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import quizrr.quizrr.com.R;

public class TermsandPolicyActivity_ViewBinding implements Unbinder {
  private TermsandPolicyActivity target;

  @UiThread
  public TermsandPolicyActivity_ViewBinding(TermsandPolicyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TermsandPolicyActivity_ViewBinding(TermsandPolicyActivity target, View source) {
    this.target = target;

    target.termslinearlayout = Utils.findRequiredViewAsType(source, R.id.termslinearlayout, "field 'termslinearlayout'", LinearLayout.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.titletextview, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TermsandPolicyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.termslinearlayout = null;
    target.title = null;
  }
}
