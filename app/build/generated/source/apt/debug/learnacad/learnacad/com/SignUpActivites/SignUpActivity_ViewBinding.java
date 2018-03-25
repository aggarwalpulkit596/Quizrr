// Generated code from Butter Knife. Do not modify!
package learnacad.learnacad.com.SignUpActivites;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import learnacad.learnacad.com.R;

public class SignUpActivity_ViewBinding implements Unbinder {
  private SignUpActivity target;

  @UiThread
  public SignUpActivity_ViewBinding(SignUpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignUpActivity_ViewBinding(SignUpActivity target, View source) {
    this.target = target;

    target.signupnumberedittext = Utils.findRequiredViewAsType(source, R.id.signupnumberedittext, "field 'signupnumberedittext'", EditText.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.signupconstraintlayout, "field 'relativeLayout'", RelativeLayout.class);
    target.continuebtn = Utils.findRequiredViewAsType(source, R.id.button3, "field 'continuebtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignUpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.signupnumberedittext = null;
    target.relativeLayout = null;
    target.continuebtn = null;
  }
}
