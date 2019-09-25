package com.uestc.medicine.net;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PengFeifei on 2018/10/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {


    public RetBean ret;
    public List<DcotorBean> dcotor;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RetBean {

        public boolean success;
        public String message;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DcotorBean {
        public String doctor_id;
        public String name;
        public String telephone;
        public Object zone;
        public String hospital;
        public String profession;
        public String company_id;
        public String bonus;
        public String used_bonus;
    }
}
