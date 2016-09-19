package ru.store.controllers.models;

import java.util.List;
import java.util.Map;

/**
 * home.jsp
 * HomeController
 */
public class HomeModel {
    private List<PartitionItem> partitionItems;
    private Map<Integer, List<SubPartitionItem>> subPartitionItemsGroupByPartition;
    private Map<Integer, List<BestCompanyItem>> bestCompanyItems;

    public List<PartitionItem> getPartitionItems() {
        return partitionItems;
    }
    public void setPartitionItems(List<PartitionItem> partitionItems) {
        this.partitionItems = partitionItems;
    }
    public Map<Integer, List<BestCompanyItem>> getBestCompanyItems() {
        return bestCompanyItems;
    }
    public void setBestCompanyItems(Map<Integer, List<BestCompanyItem>> bestCompanyItems) {
        this.bestCompanyItems = bestCompanyItems;
    }
    public Map<Integer, List<SubPartitionItem>> getSubPartitionItemsGroupByPartition() {
        return subPartitionItemsGroupByPartition;
    }
    public void setSubPartitionItemsGroupByPartition(Map<Integer, List<SubPartitionItem>> subPartitionItemsGroupByPartition) {
        this.subPartitionItemsGroupByPartition = subPartitionItemsGroupByPartition;
    }

    public static class PartitionItem {
        private int partitionId;
        private String partitionName;
        private int companyCount;

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
        public int getCompanyCount() {
            return companyCount;
        }
        public void setCompanyCount(int companyCount) {
            this.companyCount = companyCount;
        }
    }

    public static class BestCompanyItem {
        private int companyId;
        private String companyLogoFileName;
        private String companyName;

        public int getCompanyId() {
            return companyId;
        }
        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }
        public String getCompanyLogoFileName() {
            return companyLogoFileName;
        }
        public void setCompanyLogoFileName(String companyLogoFileName) {
            this.companyLogoFileName = companyLogoFileName;
        }
        public String getCompanyName() {
            return companyName;
        }
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
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
