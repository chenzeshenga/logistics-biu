package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * l_template
 *
 * @author
 */
public class Template implements Serializable {
    private String uuid;

    private Date createOn;

    private String fileName;

    private byte[] templateFile;

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(byte[] templateFile) {
        this.templateFile = templateFile;
    }

    @Override public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Template other = (Template)that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid())) && (this.getCreateOn() == null ?
            other.getCreateOn() == null : this.getCreateOn().equals(other.getCreateOn())) && (this.getFileName() == null ?
            other.getFileName() == null : this.getFileName().equals(other.getFileName())) && (Arrays
            .equals(this.getTemplateFile(), other.getTemplateFile()));
    }

    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getCreateOn() == null) ? 0 : getCreateOn().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + (Arrays.hashCode(getTemplateFile()));
        return result;
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", createOn=").append(createOn);
        sb.append(", fileName=").append(fileName);
        sb.append(", templateFile=").append(templateFile);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}