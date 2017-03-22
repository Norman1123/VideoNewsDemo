package com.foxconn.norman.videonews.UI.likes;

import android.content.Context;
import android.util.AttributeSet;

import com.foxconn.norman.videonews.Bobmapi.entity.NewsEntity;
import com.foxconn.norman.videonews.Bobmapi.other.BombConst;
import com.foxconn.norman.videonews.Bobmapi.other.InQuery;
import com.foxconn.norman.videonews.Bobmapi.other.UserManager;
import com.foxconn.norman.videonews.Bobmapi.result.QueryResult;
import com.foxconn.norman.videonews.UI.base.BaseResourceView;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class LikesListView extends BaseResourceView<NewsEntity,LikesItemView>{
    public LikesListView(Context context) {
        super(context);
    }

    public LikesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LikesListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected Call<QueryResult<NewsEntity>> queryData(int limit, int skip) {
        String userId = UserManager.getInstance().getObjectId();
        //由于服务器原因造成的参数（可以直接使用）
        InQuery where = new InQuery(BombConst.FIELD_LIKES,BombConst.TABLE_USER,userId);
        return newsApi.getLikedList(limit,skip,where);

    }

    @Override
    protected int getLimit() {
        return 10;
    }

    @Override
    protected LikesItemView createItemView() {
       LikesItemView likesItemView=new LikesItemView(getContext());
        return likesItemView;
    }
    //退出登录时，清空收藏列表
    public void clear(){
        adapter.clear();
    }
}
