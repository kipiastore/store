package ru.store.controllers.callService.models;

import java.util.List;

/**
 *
 */
public class CallServicePartitionModel {
    private PartitionItem partitionItem;
    private List<SubPartitionItem> subPartitionItems;

    public PartitionItem getPartitionItem() {
        return partitionItem;
    }
    public void setPartitionItem(PartitionItem partitionItem) {
        this.partitionItem = partitionItem;
    }
    public List<SubPartitionItem> getSubPartitionItems() {
        return subPartitionItems;
    }
    public void setSubPartitionItems(List<SubPartitionItem> subPartitionItems) {
        this.subPartitionItems = subPartitionItems;
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
}
