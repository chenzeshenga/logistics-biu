package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Template implements Serializable {
    private String uuid;

    private Date create_on;

    private String file_name;

    private byte[] template_file;

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public Template withUuid(String uuid) {
        this.setUuid(uuid);
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreate_on() {
        return create_on;
    }

    public Template withCreate_on(Date create_on) {
        this.setCreate_on(create_on);
        return this;
    }

    public void setCreate_on(Date create_on) {
        this.create_on = create_on;
    }

    public String getFile_name() {
        return file_name;
    }

    public Template withFile_name(String file_name) {
        this.setFile_name(file_name);
        return this;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public byte[] getTemplate_file() {
        return template_file;
    }

    public Template withTemplate_file(byte[] template_file) {
        this.setTemplate_file(template_file);
        return this;
    }

    public void setTemplate_file(byte[] template_file) {
        this.template_file = template_file;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", create_on=").append(create_on);
        sb.append(", file_name=").append(file_name);
        sb.append(", template_file=").append(template_file);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Template other = (Template) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getCreate_on() == null ? other.getCreate_on() == null : this.getCreate_on().equals(other.getCreate_on()))
            && (this.getFile_name() == null ? other.getFile_name() == null : this.getFile_name().equals(other.getFile_name()))
            && (Arrays.equals(this.getTemplate_file(), other.getTemplate_file()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getCreate_on() == null) ? 0 : getCreate_on().hashCode());
        result = prime * result + ((getFile_name() == null) ? 0 : getFile_name().hashCode());
        result = prime * result + (Arrays.hashCode(getTemplate_file()));
        return result;
    }
}