package com.demo.callrecordcatch;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Waylon on 16/7/7.
 */
public class BaseActivity extends AppCompatActivity {

    private Map<Integer, Runnable> allowablePermissionRunnables = new HashMap<>();
    private Map<Integer, Runnable> disallowblePermissionRunnables = new HashMap<>();
    private static final String CALLLOG_PERMISSION = android.Manifest.permission.READ_CALL_LOG;
    private static final int readCallLogRequest = 2;

    public Dialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        loadingDialog = LoadingDialogUtil.createLoadingDialog(this, "数据加载中……");

        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(this,CALLLOG_PERMISSION);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions(new String[] {CALLLOG_PERMISSION},
                        readCallLogRequest);
            }else{

            }

            return;
        }



    }

    /*protected void requestPermission(int requestId, String permission,
                                     Runnable allowableRunnable, Runnable disallowableRunnable) {
        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }
        allowablePermissionRunnables.put(requestId, allowableRunnable);

        if (disallowableRunnable != null) {
            disallowblePermissionRunnables.put(requestId, disallowableRunnable);

        }

        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //检查是否拥有权限
            int checkPermission = ContextCompat.checkSelfPermission(MyApplication.getContext(), permission);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框请求授权
                ActivityCompat.requestPermissions(BaseActivity.this, new String[]{permission}, requestId);
                return;
            } else {
                allowableRunnable.run();
            }
        } else {
            allowableRunnable.run();
        }
    }
*/

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            Runnable allowRun=allowablePermissionRunnables.get(requestCode);
//            allowRun.run();
        }else {
//            Runnable disallowRun = disallowblePermissionRunnables.get(requestCode);
//            disallowRun.run();
        }
    }

}
