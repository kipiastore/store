package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.beans.EmailSender;
import ru.store.beans.GoogleCaptcha;
import ru.store.dao.interfaces.*;
import ru.store.entities.*;
import ru.store.service.*;
import ru.store.utility.Util;

import java.util.*;

/**
 *
 */
@Controller
public class PortalController {

    @Autowired
    private PartitionService partitionService;
    @Autowired
    private BestCompanyDAO bestCompanyDAO;
    @Autowired
    private SubPartitionService subPartitionService;
    @Autowired
    private GoogleCaptcha googleCaptcha;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CountingService countingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();

        //calculating portal visitors
        CountingPortalPage countingPortalPage=countingService.getCountPortalPage();
        countingPortalPage.setCountPortal();
        countingService.addCountPortalPage(countingPortalPage);
        modelAndView.addObject("countInfo","ресурса");
        modelAndView.addObject("portalCount",countingPortalPage.getCountPortal());
        //
        Map<Integer, Integer> subPartitionIdToCount = new HashMap<>();
        List<Integer> companies = companyService.getOptimizationCompanies();
        Set<Integer> availableCompany = new TreeSet<>();
        for (Integer companyId : companies) {
            availableCompany.add(companyId);
        }
        List<CompanySubPartition> companySubPartitions = companySubPartitionService.getCompanySubPartitions();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            if (!availableCompany.contains(companySubPartition.getCompanyId()))
                continue;
            if (subPartitionIdToCount.get(companySubPartition.getSubPartitionId()) == null) {
                subPartitionIdToCount.put(companySubPartition.getSubPartitionId(), 1);
            } else {
                subPartitionIdToCount.put(
                        companySubPartition.getSubPartitionId(),
                        subPartitionIdToCount.get(companySubPartition.getSubPartitionId()) + 1);
            }
        }

        // prepare part of the model;
        Map<Integer, List<Model.PartitionItem.SubPartitionItem>> subPartitionItemsGroupByPartitionId = new HashMap<>();
        List<Model.PartitionItem.SubPartitionItem> subPartitionItems;
        Model.PartitionItem.SubPartitionItem subPartitionItem;
        for (SubPartition subPartition : subPartitionService.getSubPartitions()) {
            subPartitionItem = new Model.PartitionItem.SubPartitionItem();
            subPartitionItem.subPartitionId = subPartition.getId();
            subPartitionItem.subPartitionName = getNormalName(subPartition.getName(), 24);
            if (subPartitionIdToCount.get(subPartition.getId()) != null)
                subPartitionItem.companyCount = subPartitionIdToCount.get(subPartition.getId());
            if (subPartitionItemsGroupByPartitionId.get(subPartition.getPartitionId()) != null) {
                subPartitionItemsGroupByPartitionId.get(subPartition.getPartitionId()).add(subPartitionItem);
            } else {
                subPartitionItems = new ArrayList<>();
                subPartitionItems.add(subPartitionItem);
                subPartitionItemsGroupByPartitionId.put(subPartition.getPartitionId(), subPartitionItems);
            }
        }

        for (Integer idKey : subPartitionItemsGroupByPartitionId.keySet()) {
            Collections.sort(subPartitionItemsGroupByPartitionId.get(idKey));
        }


        List<SubPartition> subPartitions = subPartitionService.getSubPartitions();
        Map<Integer, Integer> partitionIdToCount = new HashMap<>();
        for (SubPartition subPartition : subPartitions) {
            if (subPartitionIdToCount.get(subPartition.getId()) != null) {
                if (partitionIdToCount.get(subPartition.getPartitionId()) == null) {
                    partitionIdToCount.put(subPartition.getPartitionId(), subPartitionIdToCount.get(subPartition.getId()));
                } else {
                    partitionIdToCount.put(subPartition.getPartitionId(),
                            partitionIdToCount.get(subPartition.getPartitionId()) + subPartitionIdToCount.get(subPartition.getId()));
                }
            }
        }

        // prepare next part of the model;
        List<Model.PartitionItem> partitionItems = new ArrayList<>();
        Model.PartitionItem partitionItem;
        for (Partition partition : partitionService.getPartitions()) {
            partitionItem = new Model.PartitionItem();
            if (partitionIdToCount.get(partition.getId()) != null)
                partitionItem.companyCount = partitionIdToCount.get(partition.getId());
            partitionItem.partitionId = partition.getId();
            partitionItem.partitionName = getNormalName(partition.getName(), 33);
            partitionItem.subPartitionItems = subPartitionItemsGroupByPartitionId.get(partition.getId());
            partitionItems.add(partitionItem);
        }

        Collections.sort(partitionItems);

        // prepare next part of the model again;
        List<Model.BestCompanyItem> bestCompanyItems = new ArrayList<>();
        Model.BestCompanyItem bestCompanyItem;
        for (BestCompany bestCompany : bestCompanyDAO.getBestCompanies()) {
            bestCompanyItem = new Model.BestCompanyItem();
            bestCompanyItem.companyId = bestCompany.getCompany().getId();
            bestCompanyItem.companyName = bestCompany.getCompany().getName();
            bestCompanyItem.companyLogoFileName = "testLogo.jpg";
            bestCompanyItems.add(bestCompanyItem);
        }
        // prepare the model;
        int columnCount = 7;
        Map<Integer, List<Model.BestCompanyItem>> bestCompanyGroupByColumn = Util.cutPie(bestCompanyItems, columnCount);
        Model model = new Model();
        List<Model.PartitionItem> tmp1 = new ArrayList<>();
        List<Model.PartitionItem> tmp2 = new ArrayList<>();
        for (int i = 0; i < partitionItems.size(); i++) {
            if ((partitionItems.size() % 2) == 0) {
                if (i >= partitionItems.size() / 2) {
                    tmp1.add(partitionItems.get(i));
                } else {
                    tmp2.add(partitionItems.get(i));
                }
            } else {
                if (i > partitionItems.size() / 2) {
                    tmp1.add(partitionItems.get(i));
                } else {
                    tmp2.add(partitionItems.get(i));
                }
            }
        }
        model.partitionItems = tmp1;
        model.partitionItems2 = tmp2;
        model.bestCompanyGroupByColumn = bestCompanyGroupByColumn;
        // load the model to the page;

        modelAndView.addObject("model", model);
        modelAndView.setViewName("portal/index");
        return modelAndView;
    }

    private String getNormalName(String name, int length) {
        if (name != null && name.length() > length)
            return name.substring(0, length) + "..";
        else
            return name;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = main();
        model.addObject("prefix", "");
        return model;
    }

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    public ModelAndView sendMail(@RequestParam MultiValueMap<String, String> mailMap) {
        GoogleCaptcha.CaptchaResponse captchaResponse = googleCaptcha.check(mailMap.get("g-recaptcha-response").get(0));
        if (captchaResponse.success) {
            String name = mailMap.get("name").get(0);
            String email = mailMap.get("email").get(0);
            String body = mailMap.get("body").get(0);
            String type = mailMap.get("type").get(0);
            if (!name.isEmpty() &&
                    !email.isEmpty() &&
                    !body.isEmpty() &&
                    !type.isEmpty() &&
                    type.matches("\\d") &&
                    email.matches(EmailSender.EMAIL_REGEX) &&
                    email.length() < 81 &&
                    body.length() < 2001 &&
                    name.length() < 81) {
                emailSender.send(name, email, body, Integer.valueOf(type));
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }

    /**
     * model schema
     */
    public static class Model {
        public List<PartitionItem> partitionItems;
        public List<PartitionItem> partitionItems2;
        public Map<Integer, List<BestCompanyItem>> bestCompanyGroupByColumn;

        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
        }
        public Map<Integer, List<BestCompanyItem>> getBestCompanyGroupByColumn() {
            return bestCompanyGroupByColumn;
        }
        public List<PartitionItem> getPartitionItems2() {
            return partitionItems2;
        }

        public static class PartitionItem implements Comparable<PartitionItem> {
            public int partitionId;
            public String partitionName;
            public int companyCount;
            public List<SubPartitionItem> subPartitionItems;

            public int getPartitionId() {
                return partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
            public int getCompanyCount() {
                return companyCount;
            }
            public List<SubPartitionItem> getSubPartitionItems() {
                return subPartitionItems;
            }

            @Override
            public int compareTo(PartitionItem o) {
                return this.partitionName.compareTo(o.partitionName);
            }

            public static class SubPartitionItem implements Comparable<SubPartitionItem>{
                public int subPartitionId;
                public String subPartitionName;
                public int companyCount;

                @Override
                public int compareTo(SubPartitionItem o) {
                    return this.subPartitionName.compareTo(o.subPartitionName);
                }

                public int getSubPartitionId() {
                    return subPartitionId;
                }
                public String getSubPartitionName() {
                    return subPartitionName;
                }
                public int getCompanyCount() {
                    return companyCount;
                }
            }
        }

        public static class BestCompanyItem {
            public int companyId;
            public String companyLogoFileName;
            public String companyName;

            public int getCompanyId() {
                return companyId;
            }
            public String getCompanyLogoFileName() {
                return companyLogoFileName;
            }
            public String getCompanyName() {
                return companyName;
            }
        }
    }
}
