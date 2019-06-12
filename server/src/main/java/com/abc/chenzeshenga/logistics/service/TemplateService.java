package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.mapper.TemplateMapper;
import com.abc.chenzeshenga.logistics.model.CommonObj;
import com.abc.chenzeshenga.logistics.model.Template;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service public class TemplateService extends ServiceImpl<TemplateMapper, Template> {

    public Page<Template> list(Page page, CommonObj reg) {
        return page.setRecords(baseMapper.list(page,reg));
    }

}
