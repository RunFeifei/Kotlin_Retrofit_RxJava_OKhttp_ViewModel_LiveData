package com.uestc.medicine.net;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by PengFeifei on 2018/10/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu implements Serializable {


    /**
     * column : [{"column_id":"1","name":"疼痛","knowledge_qty":"48","seq":"1"},{"column_id":"2","name":"产品介绍","knowledge_qty":"32","seq":"2"},{"column_id":"4","name":"骨科","knowledge_qty":"4","seq":"3"},{"column_id":"3","name":"围术期","knowledge_qty":"48","seq":"4"},{"column_id":"5","name":"肿瘤","knowledge_qty":"8","seq":"5"}]
     * ret : {"success":"true","message":"获取成功","code":"0"}
     */

    public RetBean ret;
    public List<ColumnBean> column;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RetBean implements Serializable{
        public String success;
        public String message;
        public String code;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ColumnBean implements Serializable{
        public String column_id;
        public String name;
        public String knowledge_qty;
        public String seq;
    }

    public List<ColumnBean> getMenus() {
        if (column == null) {
            return null;
        }
        Collections.sort(column, (arg0, arg1) -> {
            return Integer.parseInt(arg0.seq)-Integer.parseInt(arg1.seq);
        });
        return column;
    }
}
