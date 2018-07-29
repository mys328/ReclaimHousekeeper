package com.duyun.huihsou.housekepper.admin.service.news;


import com.duyun.huihsou.housekepper.admin.service.AbstractBaseService;
import com.duyun.huishou.housekeeper.mapper.IBaseDao;
import com.duyun.huishou.housekeeper.mapper.NewsEntityMapper;
import com.duyun.huishou.housekeeper.po.NewsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author albert
 * @package com.xianduankeji.ktv.portal.service.user
 * @email cn.lu.duke@gmail.com
 * @date January 10, 2018
 */

@Service
@Slf4j
public class NewsServiceImpl extends AbstractBaseService<NewsEntity> implements NewsService {

    @Autowired
    private NewsEntityMapper newsEntityMapper;


    @Override
    public IBaseDao getMapper() {
        return newsEntityMapper;
    }

    @Override
    public List<NewsEntity> getAll() {
        return newsEntityMapper.selectAll(new HashMap<>());
    }

    @Override
    public Integer getNum() {
        return newsEntityMapper.selectAllCount();
    }
}
