package ru.store.controllers.callService.models;

import java.util.List;

/**
 *
 */
public class CallServiceHomeModel {
    private List<PartitionItem> partitionItems;

    public List<PartitionItem> getPartitionItems() {
        return partitionItems;
    }
    public void setPartitionItems(List<PartitionItem> partitionItems) {
        this.partitionItems = partitionItems;
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
