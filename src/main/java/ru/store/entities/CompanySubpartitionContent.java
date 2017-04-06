package ru.store.entities;

/**
 *
 */
public class CompanySubpartitionContent {

    private Integer id;
    private Integer imageId;
    private String info;
    private Integer companyId;
    private Integer companySubpartitionId;

    public CompanySubpartitionContent() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getImageId() {
        return imageId;
    }
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanySubpartitionId() {
        return companySubpartitionId;
    }

    public void setCompanySubpartitionId(Integer companySubpartitionId) {
        this.companySubpartitionId = companySubpartitionId;
    }

    @Override
    public String toString() {
        return "CompanySubpartitionContent{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", info='" + info + '\'' +
                ", companyId=" + companyId +
                ", companySubpartitionId=" + companySubpartitionId +
                '}';
    }
}
