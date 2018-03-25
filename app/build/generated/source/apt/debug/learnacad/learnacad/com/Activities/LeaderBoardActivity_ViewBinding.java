// Generated code from Butter Knife. Do not modify!
package learnacad.learnacad.com.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;
import learnacad.learnacad.com.R;

public class LeaderBoardActivity_ViewBinding implements Unbinder {
  private LeaderBoardActivity target;

  @UiThread
  public LeaderBoardActivity_ViewBinding(LeaderBoardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LeaderBoardActivity_ViewBinding(LeaderBoardActivity target, View source) {
    this.target = target;

    target.listView = Utils.findRequiredViewAsType(source, R.id.listview, "field 'listView'", ListView.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.leaderboardrootlayout, "field 'relativeLayout'", RelativeLayout.class);
    target.snackbar = Utils.findRequiredViewAsType(source, R.id.tasklayout, "field 'snackbar'", LinearLayout.class);
    target.username = Utils.findRequiredViewAsType(source, R.id.nametextview, "field 'username'", TextView.class);
    target.usertime = Utils.findRequiredViewAsType(source, R.id.timetextview, "field 'usertime'", TextView.class);
    target.userposition = Utils.findRequiredViewAsType(source, R.id.positiontextvieww, "field 'userposition'", TextView.class);
    target.topic = Utils.findRequiredViewAsType(source, R.id.topictextview, "field 'topic'", TextView.class);
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
    LeaderBoardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listView = null;
    target.relativeLayout = null;
    target.snackbar = null;
    target.username = null;
    target.usertime = null;
    target.userposition = null;
    target.topic = null;
    target.images = null;
    target.imagetext = null;
  }
}
