package com.foxconn.norman.videonews.Bobmapi.other;

import com.foxconn.norman.videonews.Bobmapi.entity.CommentsEntity;
import com.foxconn.norman.videonews.Bobmapi.entity.NewsEntity;
import com.foxconn.norman.videonews.Bobmapi.entity.PublishEntity;
import com.foxconn.norman.videonews.Bobmapi.result.CollectResult;
import com.foxconn.norman.videonews.Bobmapi.result.CommentsResult;
import com.foxconn.norman.videonews.Bobmapi.result.QueryResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public interface NewsApi {

    //获取新闻列表,排序方式，接时间新到旧排序
    @GET("1/classes/News?order=-createdAt")
    Call<QueryResult<NewsEntity>> getVideoNewsList(
            @Query("limit") int limit,
            @Query("skip") int skip);

    //获取新闻的所有评论，按时间从新到旧来排序
    //注意，我们希望评论作者不仅返回objectId，还返回username，可以使用URL编码参数include=author
    @GET("1/classes/Comments?include=author&order=-createdAt")
    Call<QueryResult<CommentsEntity>> getComments(
            @Query("limit") int limit,
            @Query("skip") int skip,
            @Query("where") InQuery where);

    //发表评论
    @POST("1/classes/Comments")
    Call<CommentsResult> postComments(@Body PublishEntity publishEntity);

    //获取收藏列表
    @GET("1/classes/News?order=-createdAt")
    Call<QueryResult<NewsEntity>> getLikedList(
            @Query("limit") int limit,
            @Query("skip") int skip,
            @Query("where") InQuery where
    );

    //收藏新闻
    @GET("bef74a37a08d3205/changeLike?action=like")
    Call<CollectResult> collectNews(
            @Query("newsId") String newsId,
            @Query("userId") String userId
    );
}
