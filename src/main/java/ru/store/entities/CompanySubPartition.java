package ru.store.entities;

/**
 *
 */
public class CompanySubPartition {
    private Integer id;
    private Integer companyId;
    private Integer subPartitionId;

    public CompanySubPartition() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getSubPartitionId() {
        return subPartitionId;
    }
    public void setSubPartitionId(Integer subpartitionId) {
        this.subPartitionId = subpartitionId;
    }
}
