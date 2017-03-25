package ru.store.controllers.designer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.CompanySubPartition;
import ru.store.entities.CompanySubpartitionContent;
import ru.store.entities.SubPartition;
import ru.store.service.CompanySubPartitionService;
import ru.store.service.CompanySubpartitionContentService;
import ru.store.service.SubPartitionService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class DesignerCompanyPositionsController {

    @Autowired
    private CompanySubpartitionContentService companySubpartitionContentService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;
    @Autowired
    private SubPartitionDAO subPartitionService;

    @RequestMapping(value = "/designer/positions/company/{id}", method = RequestMethod.GET)
    public ModelAndView positions(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView, Integer.valueOf(id));
        return modelAndView;
    }

    @RequestMapping(value = "/designer/addsubpartitioninfos", method = RequestMethod.POST)
    public ModelAndView updateCompany(@RequestParam("positions") String positions) {
        ModelAndView modelAndView = new ModelAndView();
        Integer companyId = null;
        try {


            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(model, modelAndView, companyId);
        return modelAndView;
    }

    private void loadPage(Model model, ModelAndView modelAndView, Integer id) {
        model.selectedPageNum = 2;
        loadCompanySubpartitionContent(model, id);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("designer/companypositions");
    }

    private void loadCompanySubpartitionContent(Model model, Integer id) {

        List<CompanySubPartition> companySubPartitions = companySubPartitionService.findCompanySubpartitionByCompanyId(id);
        List<Integer> subPartitionIds = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            subPartitionIds.add(companySubPartition.getSubPartitionId());
        }

        List<SubPartition> subPartitions = subPartitionService.getSubPartitions(subPartitionIds);

        model.companySubpartitionContentList = new ArrayList<>();
        Model.CompanySubpartitionContentItem item;
        for (CompanySubpartitionContent content : companySubpartitionContentService.getCompanySubpartitionContents(id)) {
            item = new Model.CompanySubpartitionContentItem();
            item.companyId = content.getCompanyId();
            item.companySubpartitionId = content.getCompanySubpartitionId();
            item.id = content.getId();
            item.imageId = content.getImageId();
            item.info = content.getInfo();

            for (CompanySubPartition companySubPartition : companySubPartitions) {
                if (companySubPartition.getId() != content.getCompanySubpartitionId())
                    continue;
                for (SubPartition subPartition : subPartitions) {
                    if (companySubPartition.getSubPartitionId() == subPartition.getId()) {
                        item.subPartitionName = subPartition.getName();
                    }
                }
            }
            model.companySubpartitionContentList.add(item);
        }
    }

    private String getNormalName(String name) {
        if (name != null && name.length() > 24)
            return name.substring(0, 24) + "..";
        else
            return name;
    }

    @RequestMapping(value = "/designer/addsubpartitioninfos", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/designer/positions";
    }
    @RequestMapping(value = "/designer/companypositionsearchcompany", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/designer/positions";
    }

    public static class Model {
        public int selectedPageNum;
        public String message;
        public List<CompanySubpartitionContentItem> companySubpartitionContentList;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public String getMessage() {
            return message;
        }
        public List<CompanySubpartitionContentItem> getCompanySubpartitionContentList() {
            return companySubpartitionContentList;
        }

        public static class CompanySubpartitionContentItem {
            private Integer id;
            private Integer imageId;
            private String info;
            private Integer companyId;
            private Integer companySubpartitionId;
            private String subPartitionName;

            public Integer getId() {
                return id;
            }
            public Integer getImageId() {
                return imageId;
            }
            public String getInfo() {
                return info;
            }
            public Integer getCompanyId() {
                return companyId;
            }
            public String getSubPartitionName() {
                return subPartitionName;
            }
            public Integer getCompanySubpartitionId() {
                return companySubpartitionId;
            }
        }
    }
}
