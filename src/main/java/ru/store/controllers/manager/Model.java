package ru.store.controllers.manager;

import ru.store.entities.CompanyAddress;
import ru.store.entities.CompanyReminder;
import ru.store.entities.Region;
import java.util.List;


public  class Model {
    public int selectedPageNum;
    public String addingCompanyJson;
    public String companiesJson;
    public List<Region> regions;
    public List<CompanyReminder> companyReminders;
    public int[] numOfAddress;
    public String companyAddressJson;
    public String companyReminderJson;
    public List<CompaniesItem> companyList;
    public String message;
    public int getSelectedPageNum() {
        return selectedPageNum;
    }
    public String getAddingCompanyJson() {
        return addingCompanyJson;
    }
    public String getCompaniesJson() {
        return companiesJson;
    }
    public List<Region> getRegions() {
        return regions;
    }
    public List<CompanyReminder> getCompanyReminders() {
        return companyReminders;
    }
    public int[] getNumOfAddress() {
        return numOfAddress;
    }
    public String getCompanyAddressJson() {
        return companyAddressJson;
    }
    public String getCompanyReminderJson() {
        return companyReminderJson;
    }
    public List<CompaniesItem> getCompanyList() {
        return companyList;
    }
    public String getMessage() {
        return message;
    }


    public static class CompaniesItem {
        public int id;
        public String name;
        public String directorFullName;
        public String legalAddress;
        public  List<String>companyAddresses;
        public String phone;
        public String companyPackage;
        public String debt;
        public List<String>  note;
        public String  typeOfNote;
        public String dateOfNote;
        public String commentOfNote;
        public String manager;
        public String timeOfContract;
        public Integer costOf;
        public String noteOfActs;
        public String dateOfPaid;
        public String act;
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getDirectorFullName() {
            return directorFullName;
        }
        public String getLegalAddress() {
            return legalAddress;
        }
        public String getPhone() {
            return phone;
        }
        public String getTypeOfNote(){return typeOfNote;}
        public String getDateOfNote() {
            return dateOfNote;
        }
        public String getCommentOfNote() {
            return commentOfNote;
        }
        public String getCompanyPackage() {
            return companyPackage;
        }
        public String getDebt() {
            return debt;
        }
        public List<String>  getNote() {
            return note;
        }
        public List<String> getCompanyAddresses() {
            return companyAddresses;
        }
        public String getManager() {
            return manager;
        }
        public String getTimeOfContract() {
            return timeOfContract;
        }
        public Integer getCostOf() {
            return costOf;
        }
        public String getNoteOfActs() {
            return noteOfActs;
        }
        public String getDateOfPaid() {
            return dateOfPaid;
        }
        public String getAct() {
            return act;
        }

        @Override
        public String toString() {
            return "CompaniesItem{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", directorFullName='" + directorFullName + '\'' +
                    ", legalAddress='" + legalAddress + '\'' +
                    ", phone='" + phone + '\'' +
                    ", typeOfNote='" + typeOfNote + '\'' +
                    ", dateOfNote='" + dateOfNote + '\'' +
                    ", commentOfNote='" + commentOfNote + '\'' +
                    ", debt='" + debt + '\'' +
                    ", companyPackage='" + companyPackage + '\'' +
                    ", note='" + note + '\'' +
                    ", companyAddresses='" + companyAddresses + '\'' +
                    ", manager='" + manager + '\'' +
                    ", timeOfContract='" + timeOfContract + '\'' +
                    ", costOf='" + costOf + '\'' +
                    ", noteOfActs='" + noteOfActs + '\'' +
                    ", dateOfPaid='" + dateOfPaid + '\'' +
                    ", act='" + act + '\'' +
                    '}';
        }
    }
    public static class CompanyAddressItem {
        public Integer companyId;
        public List<CompanyAddress> companyAddresses;

        public Integer getCompanyId() {
            return companyId;
        }
        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }
        public List<CompanyAddress> getCompanyAddresses() {
            return companyAddresses;
        }
        public void setCompanyAddresses(List<CompanyAddress> companyAddresses) {
            this.companyAddresses = companyAddresses;
        }
        @Override
        public String toString() {
            return "{" +
                    "\"companyId\":\"" + companyId + "\"," +
                    "\"companyAddresses\":" + companyAddresses + "" +
                    '}';
        }
    }
    public static class CompanyReminderItem {
        public Integer companyId;
        public List<CompanyReminder> companyReminders;
        public Integer getCompanyId() {
            return companyId;
        }
        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }
        public List<CompanyReminder> getCompanyReminders() {
            return companyReminders;
        }
        public void setCompanyReminders(List<CompanyReminder> companyReminders) {
            this.companyReminders = companyReminders;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"companyId\":\"" + companyId + "\"," +
                    "\"companyReminders\":" + companyReminders + "" +
                    '}';
        }
    }
}