// Generated code from Butter Knife. Do not modify!
package learnacad.learnacad.com.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;
import learnacad.learnacad.com.R;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target, View source) {
    this.target = target;

    target.circleImageView = Utils.findRequiredViewAsType(source, R.id.userImage, "field 'circleImageView'", CircleImageView.class);
    target.timetextview = Utils.findRequiredViewAsType(source, R.id.timetextview, "field 'timetextview'", TextView.class);
    target.startingintextview = Utils.findRequiredViewAsType(source, R.id.startingintextview, "field 'startingintextview'", TextView.class);
    target.notificationtextview = Utils.findRequiredViewAsType(source, R.id.notificationtextview, "field 'notificationtextview'", TextView.class);
    target.usernametextview = Utils.findRequiredViewAsType(source, R.id.usernametextview, "field 'usernametextview'", TextView.class);
    target.prizesearnedtextview = Utils.findRequiredViewAsType(source, R.id.prizesearnedtextview, "field 'prizesearnedtextview'", TextView.class);
    target.prizeamounttextview = Utils.findRequiredViewAsType(source, R.id.prizeamounttextview, "field 'prizeamounttextview'", ImageView.class);
    target.liveslefttextview = Utils.findRequiredViewAsType(source, R.id.liveslefttextview, "field 'liveslefttextview'", TextView.class);
    target.infobutton = Utils.findRequiredViewAsType(source, R.id.homeinfobutton, "field 'infobutton'", ImageButton.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.homerootlayout, "field 'relativeLayout'", RelativeLayout.class);
    target.topictextview = Utils.findRequiredViewAsType(source, R.id.topictextview, "field 'topictextview'", TextView.class);
    target.notificationimage = Utils.findRequiredViewAsType(source, R.id.notificationimage, "field 'notificationimage'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.circleImageView = null;
    target.timetextview = null;
    target.startingintextview = null;
    target.notificationtextview = null;
    target.usernametextview = null;
    target.prizesearnedtextview = null;
    target.prizeamounttextview = null;
    target.liveslefttextview = null;
    target.infobutton = null;
    target.relativeLayout = null;
    target.topictextview = null;
    target.notificationimage = null;
  }
}
