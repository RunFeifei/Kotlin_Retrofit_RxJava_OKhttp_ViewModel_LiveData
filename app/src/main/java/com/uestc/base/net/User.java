package com.uestc.base.net;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PengFeifei on 2018/10/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    @JsonProperty("profile-image")
    public String profileimage = "";
    public String avatar = "";
    public String nick = "";
    public String username = "";


}
