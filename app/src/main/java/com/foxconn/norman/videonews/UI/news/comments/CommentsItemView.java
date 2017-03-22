package com.foxconn.norman.videonews.UI.news.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.foxconn.norman.videonews.Bobmapi.entity.AuthorEntity;
import com.foxconn.norman.videonews.Bobmapi.entity.CommentsEntity;
import com.foxconn.norman.videonews.Commons.CommonUtils;
import com.foxconn.norman.videonews.R;
import com.foxconn.norman.videonews.UI.base.BaseItemView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class CommentsItemView extends BaseItemView<CommentsEntity> {

    @BindView(R.id.tvContent)
    TextView tvContent; // 评论内容
    @BindView(R.id.tvAuthor)
    TextView tvAuthor; // 评论作者
    @BindView(R.id.tvCreatedAt)
    TextView tvCreatedAt; // 评论时间

    public CommentsItemView(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_comments,this,true);
        ButterKnife.bind(this);
    }

    @Override
    protected void bindModel(CommentsEntity commentsEntity) {
        //数据绑定
        String content = commentsEntity.getContent();//评论内容
        Date createdAt = commentsEntity.getCreatedAt();// 评论时间
        AuthorEntity authorEntity = commentsEntity.getAuthor();
        String username = authorEntity.getUsername(); // 评论作者
        tvContent.setText(content);
        tvAuthor.setText(username);
        tvCreatedAt.setText(CommonUtils.format(createdAt));
    }
}
