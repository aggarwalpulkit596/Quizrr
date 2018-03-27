// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.com.SignUpActivites;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;
import quizrr.quizrr.com.R;
import quizrr.quizrr.quizrr.SignUpActivites.SigUpComplete;
import nl.dionsegijn.konfetti.KonfettiView;

public class SigUpComplete_ViewBinding implements Unbinder {
  private SigUpComplete target;

  @UiThread
  public SigUpComplete_ViewBinding(SigUpComplete target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SigUpComplete_ViewBinding(SigUpComplete target, View source) {
    this.target = target;

    target.referearned = Utils.findRequiredViewAsType(source, R.id.profileearnertextview, "field 'referearned'", RelativeLayout.class);
    target.username = Utils.findRequiredViewAsType(source, R.id.signupusername, "field 'username'", TextView.class);
    target.konfettiView = Utils.findRequiredViewAsType(source, R.id.viewKonfetti, "field 'konfettiView'", KonfettiView.class);
    target.userimage = Utils.findRequiredViewAsType(source, R.id.completeuserimage, "field 'userimage'", CircleImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SigUpComplete target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.referearned = null;
    target.username = null;
    target.konfettiView = null;
    target.userimage = null;
  }
}
