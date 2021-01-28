package web.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Information {
    private Integer informationId;
    private String informationContent;
    private String informationImage;
    private String informationCreateTime;

    @Id
    @Column(name = "InformationID", nullable = false)
    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    @Basic
    @Column(name = "InformationContent", nullable = true, length = -1)
    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }

    @Basic
    @Column(name = "InformationImage", nullable = true, length = -1)
    public String getInformationImage() {
        return informationImage;
    }

    public void setInformationImage(String informationImage) {
        this.informationImage = informationImage;
    }

    @Basic
    @Column(name = "InformationCreateTime", nullable = true, length = 2555)
    public String getInformationCreateTime() {
        return informationCreateTime;
    }

    public void setInformationCreateTime(String informationCreateTime) {
        this.informationCreateTime = informationCreateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Information that = (Information) o;

        if (informationId != null ? !informationId.equals(that.informationId) : that.informationId != null)
            return false;
        if (informationContent != null ? !informationContent.equals(that.informationContent) : that.informationContent != null)
            return false;
        if (informationImage != null ? !informationImage.equals(that.informationImage) : that.informationImage != null)
            return false;
        if (informationCreateTime != null ? !informationCreateTime.equals(that.informationCreateTime) : that.informationCreateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = informationId != null ? informationId.hashCode() : 0;
        result = 31 * result + (informationContent != null ? informationContent.hashCode() : 0);
        result = 31 * result + (informationImage != null ? informationImage.hashCode() : 0);
        result = 31 * result + (informationCreateTime != null ? informationCreateTime.hashCode() : 0);
        return result;
    }
}
