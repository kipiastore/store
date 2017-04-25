package ru.store.controllers.designer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.*;
import ru.store.entities.Image;
import ru.store.exceptions.NotSupportedFormat;
import ru.store.service.CompanySubPartitionService;
import ru.store.service.CompanySubpartitionContentService;
import ru.store.service.ImageService;
import ru.store.service.SubPartitionService;
import ru.store.servlets.DownloadImage;
import ru.store.servlets.DownloadServlet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
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
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/designer/positions/company/{id}", method = RequestMethod.GET)
    public ModelAndView positions(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView, Integer.valueOf(id));
        return modelAndView;
    }

    public static BufferedImage convertToBufferedImage(java.awt.Image image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    @RequestMapping(value = "/designer/addsubpartitioninfos", method = RequestMethod.POST)
    public String updateCompany(@ModelAttribute("companySubpartitionContent") CompanySubpartitionContent companySubpartitionContent,
                                @RequestParam("file") MultipartFile multipartFile,
                                @RequestParam("companySubpartitionId") String companySubpartitionId) {
        try {
            String[] tmp = multipartFile.getOriginalFilename().split("\\.");
            if (tmp.length >= 2) {
                if (DownloadImage.FILE_TYPE_TO_CONTENT_TYPE.get(tmp[tmp.length-1]) == null) {
                    throw new NotSupportedFormat("Этот формат не поддерживается.");
                }
                Image image = new Image();
                BufferedImage imageData = ImageIO.read(new ByteArrayInputStream(multipartFile.getBytes()));
                int currentHeight = imageData.getHeight();
                int currentWidth = imageData.getWidth();
                if (currentHeight > 250) {

                    double newWidth = currentWidth * 250 / currentHeight;
                    java.awt.Image scaled = imageData.getScaledInstance((int) newWidth, 250, java.awt.Image.SCALE_SMOOTH);

                    Graphics2D g = imageData.createGraphics();
                    g.drawImage(scaled, 0, 0, null);
                    g.dispose();


                    BufferedImage bufferedImage = convertToBufferedImage(scaled);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, tmp[tmp.length-1], baos);
                    baos.flush();
                    image.setFile(baos.toByteArray());
                    baos.close();
                } else {
                    image.setFile(multipartFile.getBytes());
                }
                image.setName(multipartFile.getOriginalFilename());
                imageService.createImage(image);
                companySubpartitionContent.setImageId(image.getId());
            }
            companySubpartitionContent.setCompanySubpartitionId(Integer.valueOf(companySubpartitionId));
            companySubpartitionContentService.createCompanySubpartitionContent(companySubpartitionContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/designer/positions/company/" + companySubpartitionContent.getCompanyId();
    }

    @RequestMapping(value = "/designer/deletesubpartitioninfos", method = RequestMethod.POST)
    public String deleteCompany(@RequestParam("companyId") String companyId,
                                @RequestParam("companySubpartitionContentId") String companySubpartitionContentId) {
        try {
            companySubpartitionContentService.deleteCompanySubpartitionContent(Integer.valueOf(companySubpartitionContentId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/designer/positions/company/" + companyId;
    }

    @RequestMapping(value = "/designer/editsubpartitioninfos", method = RequestMethod.POST)
    public String editCompany(@ModelAttribute("companySubpartitionContent") CompanySubpartitionContent companySubpartitionContent,
                              @RequestParam("file") MultipartFile multipartFile,
                              @RequestParam("contentId") String contentId,
                              @RequestParam("companySubpartitionId") String companySubpartitionId,
                              @RequestParam("imageId") String imageId) {
        try {
            String[] tmp = multipartFile.getOriginalFilename().split("\\.");
            if (tmp.length >= 2) {
                if (DownloadImage.FILE_TYPE_TO_CONTENT_TYPE.get(tmp[tmp.length-1]) == null) {
                    throw new NotSupportedFormat("Этот формат не поддерживается.");
                }
                Image image = new Image();
                image.setFile(multipartFile.getBytes());
                image.setName(multipartFile.getOriginalFilename());
                imageService.createImage(image);
                imageService.deleteImage(Integer.valueOf(imageId));
                companySubpartitionContent.setImageId(image.getId());
            } else {
                companySubpartitionContent.setImageId(Integer.valueOf(imageId));
            }

            companySubpartitionContent.setId(Integer.valueOf(contentId));
            companySubpartitionContent.setCompanySubpartitionId(Integer.valueOf(companySubpartitionId));
            companySubpartitionContentService.updateCompanySubpartitionContent(companySubpartitionContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/designer/positions/company/" + companySubpartitionContent.getCompanyId();
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
                if (!Objects.equals(companySubPartition.getId(), content.getCompanySubpartitionId()))
                    continue;
                for (SubPartition subPartition : subPartitions) {
                    if (companySubPartition.getSubPartitionId() == subPartition.getId()) {
                        item.subPartitionName = subPartition.getName();
                    }
                }
            }
            model.companySubpartitionContentList.add(item);
        }
        model.companyId = id;

        model.subPartitionItemList = new ArrayList<>();
        Model.SubPartitionItem subPartitionItem;
        List<CompanySubpartitionContent> currentContents = companySubpartitionContentService.getCompanySubpartitionContents(id);


        Set<Integer> currentContentSet = new HashSet<>();
        for (CompanySubpartitionContent content : currentContents) {
            currentContentSet.add(content.getCompanySubpartitionId());
        }

        for (CompanySubPartition companySubPartition : companySubPartitions) {
            subPartitionItem = new Model.SubPartitionItem();
            subPartitionItem.companySubpartitionId = companySubPartition.getId();
            for (SubPartition subPartition : subPartitions) {
                if (subPartition.getId() == companySubPartition.getSubPartitionId()) {
                    subPartitionItem.subpartitionName = subPartition.getName();
                }
            }

            if (!currentContentSet.contains(companySubPartition.getId()))
                model.subPartitionItemList.add(subPartitionItem);
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
    @RequestMapping(value = "/designer/deletesubpartitioninfos", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/designer/positions";
    }
    @RequestMapping(value = "/designer/editsubpartitioninfos", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/designer/positions";
    }
    @RequestMapping(value = "/designer/companypositionsearchcompany", method = RequestMethod.GET)
    public String redirect4() {
        return "redirect:/designer/positions";
    }

    public static class Model {
        public int selectedPageNum;
        public String message;
        public Integer companyId;
        public List<CompanySubpartitionContentItem> companySubpartitionContentList;
        public List<SubPartitionItem> subPartitionItemList;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public String getMessage() {
            return message;
        }
        public List<CompanySubpartitionContentItem> getCompanySubpartitionContentList() {
            return companySubpartitionContentList;
        }
        public Integer getCompanyId() {
            return companyId;
        }
        public List<SubPartitionItem> getSubPartitionItemList() {
            return subPartitionItemList;
        }

        public static class CompanySubpartitionContentItem {
            public Integer id;
            public Integer imageId;
            public String info;
            public Integer companyId;
            public Integer companySubpartitionId;
            public String subPartitionName;

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

        public static class SubPartitionItem {
            public Integer companySubpartitionId;
            public String subpartitionName;

            public Integer getCompanySubpartitionId() {
                return companySubpartitionId;
            }
            public String getSubpartitionName() {
                return subpartitionName;
            }
        }
    }
}
