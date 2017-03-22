package com.foxconn.norman.videonews.Bobmapi.entity;

//      {
//        "content":评论内容,
//        "author":{
    //        "__type":"Pointer",
    //        "className":"_User",
    //        "objectId":用户Id
//              }
//        "news":{
    //        "__type":"Pointer",
    //        "className":"News",
    //        "objectId":新闻Id
//              }
//     }


import com.foxconn.norman.videonews.Bobmapi.other.AuthorPointer;
import com.foxconn.norman.videonews.Bobmapi.other.NewsPointer;

public class PublishEntity {
    private String content;
    private AuthorPointer author;
    private NewsPointer news;

    public PublishEntity(String content, String userId, String newsId) {
        this.content = content;
        this.author = new AuthorPointer(userId);
        this.news = new NewsPointer(newsId);
    }
}
