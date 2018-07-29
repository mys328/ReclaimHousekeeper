package com.duyun.huihsou.housekepper.admin.controller.banner;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.duyun.huihsou.housekepper.admin.request.BannerConfigParams;
import com.duyun.huihsou.housekepper.admin.service.banner.BannerChannelService;
import com.duyun.huihsou.housekepper.admin.service.banner.BannerConfigService;
import com.duyun.huishou.housekeeper.po.BannerChannelEntity;
import com.duyun.huishou.housekeeper.po.BannerConfigEntity;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author haoshijing
 * @version 2018年05月08日 21:13
 **/
@Controller
@RequestMapping("/banner")
public class BannerController {

    private Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private BannerConfigService bannerConfigService;

    @Autowired
    private BannerChannelService bannerChannelService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "banner-list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String getList() {
        List<BannerConfigEntity> list = bannerConfigService.getList();

        return JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue);
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        BannerConfigEntity entity = bannerConfigService.selectByPrimaryKey(id);
        List<BannerChannelEntity> list = bannerChannelService.getList();
        if (entity!=null && CollectionUtils.isNotEmpty(list)){
            model.addAttribute("entity", entity);
            model.addAttribute("list", list);
        }
        return "banner-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add( Model model) {
        BannerConfigEntity entity = new BannerConfigEntity();
        List<BannerChannelEntity> list = bannerChannelService.getList();
        model.addAttribute("entity", entity);
        model.addAttribute("list", list);
        return "banner-add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(BannerConfigParams params) {

        BannerConfigEntity entity = new BannerConfigEntity();
        BeanUtils.copyProperties(params, entity);
        if (entity.getId()!=null){
            bannerConfigService.updateByPrimaryKeySelective(entity);
        } else {
            bannerConfigService.insert(entity);
        }

        return JSONObject.toJSONString(true);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        try {
            bannerConfigService.deleteByPrimaryKey(id);
        }catch (Exception e) {
            logger.error(e.getMessage());
            return JSONObject.toJSONString("error");
        }

        return JSONObject.toJSONString("ok");
    }
}
