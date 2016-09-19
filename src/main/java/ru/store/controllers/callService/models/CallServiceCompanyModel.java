package ru.store.controllers.callService.models;

/**
 *
 */
public class CallServiceCompanyModel {
    private int companyId;
    private String companyName;
    private SubPartitionItem subPartitionItem;

    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public SubPartitionItem getSubPartitionItem() {
        return subPartitionItem;
    }
    public void setSubPartitionItem(SubPartitionItem subPartitionItem) {
        this.subPartitionItem = subPartitionItem;
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
}
