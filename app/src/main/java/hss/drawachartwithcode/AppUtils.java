package hss.drawachartwithcode;

import android.content.res.Resources;

/**
 * Created by dinhdv on 12/7/2017.
 */

public class AppUtils {
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
