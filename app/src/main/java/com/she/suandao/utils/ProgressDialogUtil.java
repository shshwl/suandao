package com.she.suandao.utils;

import android.content.Context;

import com.mrj.framworklib.view.CustomProgressDialog;

/**
 * loading管理
 *
 * @author 49829
 * @date 2017/11/28
 */

public class ProgressDialogUtil {

    private static CustomProgressDialog dialog;

    public static void showDialog(final Context context, String content) {
        dialog = new CustomProgressDialog(context, CustomProgressDialog.THEME_DARK, content);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }


}
