package com.foxconn.norman.videonews.UI.news.comments;

import android.content.Context;
import android.util.AttributeSet;

import com.foxconn.norman.videonews.Bobmapi.entity.CommentsEntity;
import com.foxconn.norman.videonews.Bobmapi.other.BombConst;
import com.foxconn.norman.videonews.Bobmapi.other.InQuery;
import com.foxconn.norman.videonews.Bobmapi.result.QueryResult;
import com.foxconn.norman.videonews.UI.base.BaseResourceView;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class CommentsListView extends BaseResourceView<CommentsEntity,CommentsItemView> {
    public CommentsListView(Context context) {
        super(context);
    }

    public CommentsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String newsId;

    public void setNewsId(String newsId){
        this.newsId = newsId;
    }

    @Override
    protected Call<QueryResult<CommentsEntity>> queryData(int limit, int skip) {
        InQuery where = new InQuery(BombConst.FIELD_NEWS,BombConst.TABLE_NEWS,newsId);
        return newsApi.getComments(limit,skip,where);
    }

    @Override
    protected int getLimit() {
        return 20;
    }

    @Override
    protected CommentsItemView createItemView() {
        return new CommentsItemView(getContext());
    }
}
