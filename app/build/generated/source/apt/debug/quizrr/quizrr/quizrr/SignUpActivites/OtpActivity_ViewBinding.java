// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.quizrr.SignUpActivites;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import quizrr.quizrr.com.R;

public class OtpActivity_ViewBinding implements Unbinder {
  private OtpActivity target;

  @UiThread
  public OtpActivity_ViewBinding(OtpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OtpActivity_ViewBinding(OtpActivity target, View source) {
    this.target = target;

    target.editText1 = Utils.findRequiredViewAsType(source, R.id.otpedittext1, "field 'editText1'", EditText.class);
    target.editText2 = Utils.findRequiredViewAsType(source, R.id.otpedittext2, "field 'editText2'", EditText.class);
    target.editText3 = Utils.findRequiredViewAsType(source, R.id.otpedittext3, "field 'editText3'", EditText.class);
    target.editText4 = Utils.findRequiredViewAsType(source, R.id.otpedittext4, "field 'editText4'", EditText.class);
    target.timerTextView = Utils.findRequiredViewAsType(source, R.id.otpresettimer, "field 'timerTextView'", TextView.class);
    target.submitButton = Utils.findRequiredViewAsType(source, R.id.otpsumbitbutton, "field 'submitButton'", Button.class);
    target.otpResendButton = Utils.findRequiredViewAsType(source, R.id.otpResendButton, "field 'otpResendButton'", Button.class);
    target.constraintLayout = Utils.findRequiredViewAsType(source, R.id.optconstraintlayout, "field 'constraintLayout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OtpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editText1 = null;
    target.editText2 = null;
    target.editText3 = null;
    target.editText4 = null;
    target.timerTextView = null;
    target.submitButton = null;
    target.otpResendButton = null;
    target.constraintLayout = null;
  }
}
