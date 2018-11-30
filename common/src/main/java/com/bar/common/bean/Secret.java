package com.bar.common.bean;

import com.bar.common.number.RandomUtil;

import java.util.Date;

public class Secret {

    private Integer id;
    private String transmi;
    private String secret = RandomUtil.getUUID();
    private String receiver;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public Secret setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTransmi() {
        return transmi;
    }

    public Secret setTransmi(String transmi) {
        this.transmi = transmi;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public Secret setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public Secret setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Secret setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Secret setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
