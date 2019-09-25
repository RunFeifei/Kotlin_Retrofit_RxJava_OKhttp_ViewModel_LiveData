package com.uestc.medicine.net;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PengFeifei on 2018/10/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KnowledgeDetails implements Serializable {


    public RetBean ret;
    public List<KnowledgeBean> knowledge;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RetBean implements Serializable{

        public String success;
        public String message;
        public String code;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KnowledgeBean implements Serializable{
        public String knowledge_id;
        public String name;
        public Object note;
        public String url;
        public String face_url;
        public String for_user;
        public String study_time;
        public String browse_qty;
        public String column_name;
    }
}
