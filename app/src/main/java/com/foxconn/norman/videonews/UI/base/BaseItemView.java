package com.foxconn.norman.videonews.UI.base;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public abstract class BaseItemView<Model> extends FrameLayout{
    public BaseItemView(Context context) {
        super(context);
        initView();
    }
    //初始化当前视图
    protected abstract void initView();
    //将实体数据绑定到当前视图上
    protected abstract void bindModel(Model model);
}
