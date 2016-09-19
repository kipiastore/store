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
        private PartitionItem partitionItem;

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
        public PartitionItem getPartitionItem() {
            return partitionItem;
        }
        public void setPartitionItem(PartitionItem partitionItem) {
            this.partitionItem = partitionItem;
        }
    }

    public static class PartitionItem {
        private int partitionId;
        private String partitionName;

        public int getPartitionId() {
            return partitionId;
        }
        public void setPartitionId(int partitionId) {
            this.partitionId = partitionId;
        }
        public String getPartitionName() {
            return partitionName;
        }
        public void setPartitionName(String partitionName) {
            this.partitionName = partitionName;
        }
    }

    public static class CompanyItem {
        private int companyId;
        private String companyName;

        public int getCompanyId() {
            return companyId;
        }
        public void setCompanyId(int companyInt) {
            this.companyId = companyInt;
        }
        public String getCompanyName() {
            return companyName;
        }
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
}
