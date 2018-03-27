// Generated code from Butter Knife. Do not modify!
package quizrr.quizrr.com.SignUpActivites;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;
import quizrr.quizrr.com.R;
import quizrr.quizrr.quizrr.SignUpActivites.ProfileActivity;

public class ProfileActivity_ViewBinding implements Unbinder {
  private ProfileActivity target;

  @UiThread
  public ProfileActivity_ViewBinding(ProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileActivity_ViewBinding(ProfileActivity target, View source) {
    this.target = target;

    target.constraintLayout = Utils.findRequiredViewAsType(source, R.id.profileconstraintlayout, "field 'constraintLayout'", RelativeLayout.class);
    target.usernamedittext = Utils.findRequiredViewAsType(source, R.id.profilenameedittext, "field 'usernamedittext'", EditText.class);
    target.referraledittext = Utils.findRequiredViewAsType(source, R.id.profilereferraledittext, "field 'referraledittext'", EditText.class);
    target.sumbitbtn = Utils.findRequiredViewAsType(source, R.id.profilesumbitbutton, "field 'sumbitbtn'", Button.class);
    target.userImage = Utils.findRequiredViewAsType(source, R.id.userimage2nd, "field 'userImage'", CircleImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.constraintLayout = null;
    target.usernamedittext = null;
    target.referraledittext = null;
    target.sumbitbtn = null;
    target.userImage = null;
  }
}
