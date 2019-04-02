package com.abc.chenzeshenga.logistics.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension
 * Description:
 * 
 *
 * @author 
 */
public class Template implements Serializable {
    /**
     *
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    private String uuid;

    /**
     *
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    private Date create_on;

    /**
     *
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    private String file_name;

    /**
     *
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    private byte[] template_file;

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column logistics-prod..l_template.uuid
     *
     * @return the value of logistics-prod..l_template.uuid
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public Template withUuid(String uuid) {
        this.setUuid(uuid);
        return this;
    }

    /**
     * This method sets the value of the database column logistics-prod..l_template.uuid
     *
     * @param uuid the value for logistics-prod..l_template.uuid
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * This method returns the value of the database column logistics-prod..l_template.create_on
     *
     * @return the value of logistics-prod..l_template.create_on
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public Date getCreate_on() {
        return create_on;
    }

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public Template withCreate_on(Date create_on) {
        this.setCreate_on(create_on);
        return this;
    }

    /**
     * This method sets the value of the database column logistics-prod..l_template.create_on
     *
     * @param create_on the value for logistics-prod..l_template.create_on
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public void setCreate_on(Date create_on) {
        this.create_on = create_on;
    }

    /**
     * This method returns the value of the database column logistics-prod..l_template.file_name
     *
     * @return the value of logistics-prod..l_template.file_name
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public String getFile_name() {
        return file_name;
    }

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public Template withFile_name(String file_name) {
        this.setFile_name(file_name);
        return this;
    }

    /**
     * This method sets the value of the database column logistics-prod..l_template.file_name
     *
     * @param file_name the value for logistics-prod..l_template.file_name
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    /**
     * This method returns the value of the database column logistics-prod..l_template.template_file
     *
     * @return the value of logistics-prod..l_template.template_file
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public byte[] getTemplate_file() {
        return template_file;
    }

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public Template withTemplate_file(byte[] template_file) {
        this.setTemplate_file(template_file);
        return this;
    }

    /**
     * This method sets the value of the database column logistics-prod..l_template.template_file
     *
     * @param template_file the value for logistics-prod..l_template.template_file
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    public void setTemplate_file(byte[] template_file) {
        this.template_file = template_file;
    }

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
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

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
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

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
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