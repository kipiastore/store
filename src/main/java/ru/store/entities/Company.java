package ru.store.entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 */
public class Company {

    private int id;
    private String name;
    private String keywords;
    private Date dateOfContract;
    private Date dateOfStartContract;
    private Date dateOfEndContract;
    private String manager;
    private Integer companyPackageId;
    private Integer costOf;
    private String legalName;
    private Integer inn;
    private String legalAddress;
    private String phone;
    private String fax;
    private String directorFullName;
    private String contactPerson;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private String owner;
    private String lastModifiedBy;
    private String description;
    private Boolean isShowForOperator = false;
    private Boolean isShowForSite = false;
    private Boolean isPaid = false;
    private Boolean isRedirect = false;
    private Boolean isOffPosition = false;
    private Boolean isClosed = false;
    private Boolean isPriority = false;
    private String email;
    private String site;
    private Integer countCompany=0;
    private Integer countCompanyToday=0;
    private Integer imageId;

    //private String site;
    //private Partition partition;
    //private Collection<SubPartition> subPartitions;
    //private String logo;

    public Company() {
        Timestamp tmp = new Timestamp(new java.util.Date().getTime());
        createdDate = tmp;
        lastModifiedDate = tmp;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public Date getDateOfContract() {
        return dateOfContract;
    }
    public void setDateOfContract(Date dateOfContract) {
        this.dateOfContract = dateOfContract;
    }
    public Date getDateOfStartContract() {
        return dateOfStartContract;
    }
    public void setDateOfStartContract(Date dateOfStartContract) {
        this.dateOfStartContract = dateOfStartContract;
    }
    public Date getDateOfEndContract() {
        return dateOfEndContract;
    }
    public void setDateOfEndContract(Date dateOfEndContract) {
        this.dateOfEndContract = dateOfEndContract;
    }
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    public Integer getCompanyPackageId() {
        return companyPackageId;
    }
    public void setCompanyPackageId(Integer companyPackageId) {
        this.companyPackageId = companyPackageId;
    }
    public Integer getCostOf() {
        return costOf;
    }
    public void setCostOf(Integer costOf) {
        this.costOf = costOf;
    }
    public String getLegalName() {
        return legalName;
    }
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    public Integer getInn() {
        return inn;
    }
    public void setInn(Integer inn) {
        this.inn = inn;
    }
    public String getLegalAddress() {
        return legalAddress;
    }
    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getDirectorFullName() {
        return directorFullName;
    }
    public void setDirectorFullName(String directorFullName) {
        this.directorFullName = directorFullName;
    }
    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getIsShowForOperator() {
        return isShowForOperator;
    }
    public void setIsShowForOperator(Boolean isShowForOperator) {
        this.isShowForOperator = isShowForOperator;
    }
    public Boolean getIsShowForSite() {
        return isShowForSite;
    }
    public void setIsShowForSite(Boolean isShowForSite) {
        this.isShowForSite = isShowForSite;
    }
    public Boolean getIsPaid() {
        return isPaid;
    }
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
    public Boolean getIsRedirect() {
        return isRedirect;
    }
    public void setIsRedirect(Boolean isRedirect) {
        this.isRedirect = isRedirect;
    }
    public Boolean getIsOffPosition() {
        return isOffPosition;
    }
    public void setIsOffPosition(Boolean isOffPosition) {
        this.isOffPosition = isOffPosition;
    }
    public Boolean getIsClosed() {
        return isClosed;
    }
    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }
    public Boolean getIsPriority() {
        return isPriority;
    }
    public void setIsPriority(Boolean isPriority) {
        this.isPriority = isPriority;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
    public Integer getImageId() {
        return imageId;
    }
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
    public Integer getCountCompany() {
        return countCompany;
    }

    public void setCountCompany(Integer countCompany) {
        this.countCompany = countCompany;
    }
    public void setCountCompany() {
        this.countCompany = countCompany+1;
    }

    public Integer getCountCompanyToday() {
        return countCompanyToday;
    }

    public void setCountCompanyToday(Integer countCompanyToday) {
        this.countCompanyToday = countCompanyToday;
    }
    public void setCountCompanyToday() {
        this.countCompanyToday = countCompanyToday+1;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"keywords\":\"" + keywords + "\"," +
                "\"dateOfContract\":\"" + dateOfContract + "\"," +
                "\"dateOfStartContract\":\"" + dateOfStartContract + "\"," +
                "\"dateOfEndContract\":\"" + dateOfEndContract + "\"," +
                "\"manager\":\"" + manager + "\"," +
                "\"companyPackageId\":\"" + companyPackageId + "\"," +
                "\"costOf\":\"" + costOf + "\"," +
                "\"legalName\":\"" + legalName + "\"," +
                "\"inn\":\"" + inn + "\"," +
                "\"legalAddress\":\"" + legalAddress + "\"," +
                "\"phone\":\"" + phone + "\"," +
                "\"fax\":\"" + fax + "\"," +
                "\"directorFullName\":\"" + directorFullName + "\"," +
                "\"contactPerson\":\"" + contactPerson + "\"," +
                "\"isShowForOperator\":\"" + isShowForOperator + "\"," +
                "\"isShowForSite\":\"" + isShowForSite + "\"," +
                "\"isPaid\":\"" + isPaid + "\"," +
                "\"isRedirect\":\"" + isRedirect + "\"," +
                "\"isOffPosition\":\"" + isOffPosition + "\"," +
                "\"isClosed\":\"" + isClosed + "\"," +
                "\"isPriority\":\"" + isPriority + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"site\":\"" + site + "\"," +
                "\"imageId\":\"" + imageId + "\"," +
                "\"description\":\"" + description + "\"" +
                '}';
    }
}

