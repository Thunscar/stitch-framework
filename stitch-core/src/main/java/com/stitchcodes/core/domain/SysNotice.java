package com.stitchcodes.core.domain;

import com.stitchcodes.common.domain.BaseEntity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @TableName sys_notice
 */
public class SysNotice extends BaseEntity implements Serializable {
    /**
     * 通知ID
     */
    private Long noticeId;

    /**
     * 通知标题
     */
    private String noticeTitle;

    /**
     * 通知类型(0通知1公告)
     */
    private String noticeType;

    /**
     * 状态(0正常1关闭)
     */
    private String status;

    /**
     * 通知内容
     */
    private byte[] noticeContent;

    private static final long serialVersionUID = 1L;

    /**
     * 通知ID
     */
    public Long getNoticeId() {
        return noticeId;
    }

    /**
     * 通知ID
     */
    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * 通知标题
     */
    public String getNoticeTitle() {
        return noticeTitle;
    }

    /**
     * 通知标题
     */
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    /**
     * 通知类型(0通知1公告)
     */
    public String getNoticeType() {
        return noticeType;
    }

    /**
     * 通知类型(0通知1公告)
     */
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * 状态(0正常1关闭)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态(0正常1关闭)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 通知内容
     */
    public byte[] getNoticeContent() {
        return noticeContent;
    }

    /**
     * 通知内容
     */
    public void setNoticeContent(byte[] noticeContent) {
        this.noticeContent = noticeContent;
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
        SysNotice other = (SysNotice) that;
        return (this.getNoticeId() == null ? other.getNoticeId() == null : this.getNoticeId().equals(other.getNoticeId()))
            && (this.getNoticeTitle() == null ? other.getNoticeTitle() == null : this.getNoticeTitle().equals(other.getNoticeTitle()))
            && (this.getNoticeType() == null ? other.getNoticeType() == null : this.getNoticeType().equals(other.getNoticeType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (Arrays.equals(this.getNoticeContent(), other.getNoticeContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNoticeId() == null) ? 0 : getNoticeId().hashCode());
        result = prime * result + ((getNoticeTitle() == null) ? 0 : getNoticeTitle().hashCode());
        result = prime * result + ((getNoticeType() == null) ? 0 : getNoticeType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + (Arrays.hashCode(getNoticeContent()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", noticeId=").append(noticeId);
        sb.append(", noticeTitle=").append(noticeTitle);
        sb.append(", noticeType=").append(noticeType);
        sb.append(", status=").append(status);
        sb.append(", noticeContent=").append(noticeContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}