package com.she.suandao.activity;

import com.she.suandao.R;
import com.she.suandao.base.BaseApplication;
import com.she.suandao.base.BaseFragmentActivity;
import com.she.suandao.entity.greendao.Buddha;
import com.she.suandao.entity.greendao.DaoSession;
import com.she.suandao.entity.greendao.Tribute;


import java.util.List;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/24
 * Time : 18:27
 */
public class BlessingActivity extends BaseFragmentActivity {

    private DaoSession daoSession;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_blessing;
    }

    @Override
    protected void initData() {
        daoSession = BaseApplication.getInstance().getDaoSession();
        queryBuddhaList();
        queryTributeList();
    }

    /**
     * @return 佛像列表
     */
    private List queryBuddhaList() {
        List<Buddha> buddhas = daoSession.loadAll(Buddha.class);
        return buddhas;
    }

    /**
     * @return 贡品列表
     */
    private List queryTributeList() {
        List<Tribute> tributes = daoSession.loadAll(Tribute.class);
        return tributes;
    }


}
