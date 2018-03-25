// Generated code from Butter Knife. Do not modify!
package learnacad.learnacad.com.InfoActivies;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;
import learnacad.learnacad.com.R;
import nl.dionsegijn.konfetti.KonfettiView;

public class Winners_ViewBinding implements Unbinder {
  private Winners target;

  @UiThread
  public Winners_ViewBinding(Winners target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Winners_ViewBinding(Winners target, View source) {
    this.target = target;

    target.confetti = Utils.findRequiredViewAsType(source, R.id.winnerconfetti, "field 'confetti'", KonfettiView.class);
    target.winnermessage = Utils.findRequiredViewAsType(source, R.id.winnermessage, "field 'winnermessage'", TextView.class);
    target.winnerusername = Utils.findRequiredViewAsType(source, R.id.winnerusername, "field 'winnerusername'", TextView.class);
    target.winnerprizetext = Utils.findRequiredViewAsType(source, R.id.winnerprizetext, "field 'winnerprizetext'", TextView.class);
    target.winnnermoney = Utils.findRequiredViewAsType(source, R.id.winnermoney, "field 'winnnermoney'", TextView.class);
    target.winnerimage = Utils.findRequiredViewAsType(source, R.id.winneruserimage, "field 'winnerimage'", CircleImageView.class);
    target.winnerpostion = Utils.findRequiredViewAsType(source, R.id.winnerpostion, "field 'winnerpostion'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Winners target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.confetti = null;
    target.winnermessage = null;
    target.winnerusername = null;
    target.winnerprizetext = null;
    target.winnnermoney = null;
    target.winnerimage = null;
    target.winnerpostion = null;
  }
}
