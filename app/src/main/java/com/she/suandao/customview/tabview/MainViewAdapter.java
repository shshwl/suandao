package com.she.suandao.customview.tabview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.she.suandao.R;


/**
 * 首页tab适配器
 *
 * @author mrj
 * @date 17/4/26
 */
public class MainViewAdapter extends BaseAdapter {

    private Fragment[] fragmentArray;
    private FragmentManager fragmentManager;
    private int hasMsgIndex = 0;

    public void setHasMsgIndex(int hasMsgIndex) {
        this.hasMsgIndex = hasMsgIndex;
    }

    public MainViewAdapter(FragmentManager fragmentManager, Fragment[] fragmentArray) {
        this.fragmentManager = fragmentManager;
        this.fragmentArray = fragmentArray;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public int hasMsgIndex() {
        return hasMsgIndex;
    }


    @Override
    public String[] getTextArray() {
        return new String[]{"算道"
                , "风水"
                , "祈福台"
                , "开运"
                , "我的"
        };
    }

    @Override
    public Fragment[] getFragmentArray() {
        return fragmentArray;
    }

    @Override
    public int[] getIconImageArray() {
        return new int[]{R.mipmap.tab_suandao
                , R.mipmap.tab_fengshui
                , R.mipmap.tab_blessing
                , R.mipmap.tab_start
                , R.mipmap.tab_me};
    }

    @Override
    public int[] getSelectedIconImageArray() {
        return new int[]{R.mipmap.tab_suandao_selected
                , R.mipmap.tab_fengshui_selected
                , R.mipmap.tab_blessing_selected
                , R.mipmap.tab_start_selected
                , R.mipmap.tab_me_selected};
    }

    @Override
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
