package net.gddhy.codelivewallpaper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ( isLiveWallpaperRunning(this,getPackageName())){
            Toast.makeText(this,"已设置壁纸",Toast.LENGTH_LONG).show();
            finish();
        } else {
            startLiveWallpaperPrevivew(this, getPackageName(), getPackageName() + ".CodeLiveWallpaper");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (isLiveWallpaperRunning(this,getPackageName())) {
                //do something
                Toast.makeText(this,"已设置壁纸",Toast.LENGTH_LONG).show();
            }
        }
        finish();
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 判断一个动态壁纸是否已经在运行
     *
     * @param context
     *            :上下文
     * @param tagetPackageName
     *            :要判断的动态壁纸的包名
     * @return
     */
    public static boolean isLiveWallpaperRunning(Context context, String tagetPackageName) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);// 得到壁纸管理器
        WallpaperInfo wallpaperInfo = wallpaperManager.getWallpaperInfo();// 如果系统使用的壁纸是动态壁纸话则返回该动态壁纸的信息,否则会返回null
        if (wallpaperInfo != null) { // 如果是动态壁纸,则得到该动态壁纸的包名,并与想知道的动态壁纸包名做比较
            String currentLiveWallpaperPackageName = wallpaperInfo.getPackageName();
            if (currentLiveWallpaperPackageName.equals(tagetPackageName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 去往某个动态壁纸的预览页面,那里可以设置壁纸
     *
     * @param context
     * @param packageName
     *            动态壁纸的包名
     * @param classFullName
     *            动态壁纸service类的类全名
     */
    @SuppressLint("InlinedApi")
    public static void startLiveWallpaperPrevivew(Activity activity, String packageName, String classFullName) {
        ComponentName componentName = new ComponentName(packageName, classFullName);
        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, componentName);
        activity.startActivityForResult(intent, 100);
    }

}
