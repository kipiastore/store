package ru.store.controllers.callService.models;

import java.util.List;

/**
 *
 */
public class CallServiceSubPartitionModel {
    private SubPartitionItem subPartitionItem;
    private List<CompanyItem> companyItems;

    public SubPartitionItem getSubPartitionItem() {
        return subPartitionItem;
    }
    public void setSubPartitionItem(SubPartitionItem subPartitionItem) {
        this.subPartitionItem = subPartitionItem;
    }
    public List<CompanyItem> getCompanyItems() {
        return companyItems;
    }
    public void setCompanyItems(List<CompanyItem> companyItems) {
        this.companyItems = companyItems;
    }

    public static class SubPartitionItem {
        private int subPartitionId;
        private String subPartitionName;

        public int getSubPartitionId() {
            return subPartitionId;
        }
        public void setSubPartitionId(int subPartitionId) {
            this.subPartitionId = subPartitionId;
        }
        public String getSubPartitionName() {
            return subPartitionName;
        }
        public void setSubPartitionName(String subPartitionName) {
            this.subPartitionName = subPartitionName;
        }
    }

    public static class CompanyItem {
        private int companyInt;
        private String companyName;

        public int getCompanyInt() {
            return companyInt;
        }
        public void setCompanyInt(int companyInt) {
            this.companyInt = companyInt;
        }
        public String getCompanyName() {
            return companyName;
        }
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
}
