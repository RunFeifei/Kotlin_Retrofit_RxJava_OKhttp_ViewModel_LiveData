package com.uestc.base.net;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PengFeifei on 2018/10/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet implements Serializable {


    public String content = "";
    public SenderBean sender;
    public String error = "";
    @JsonProperty("unknown error")
    public String unknownError = "";
    public List<ImagesBean> images;
    public List<CommentsBean> comments;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SenderBean {
        public String username = "";
        public String nick = "";
        public String avatar = "";
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImagesBean {
        public String url = "";
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CommentsBean {
        public String content = "";
        public SenderBeanX sender;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class SenderBeanX {
            public String username = "";
            public String nick = "";
            public String avatar = "";
        }
    }

    public boolean isSucceed() {
        return TextUtils.isEmpty(error) && TextUtils.isEmpty(unknownError)
                && (!TextUtils.isEmpty(content) || images != null);
    }
}
