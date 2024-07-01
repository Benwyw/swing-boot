package com.benwyw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benwyw.mapper.MiscMapper;
import com.benwyw.model.Feature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MiscService {

    @Autowired
    private MiscMapper miscMapper;

    public IPage<Feature> getFeatures(int pageNumber, int limit) {
        Page<Feature> page = new Page<>(pageNumber, limit);
        page.setRecords(miscMapper.getFeatures(page));
        page.setTotal(miscMapper.getFeaturesCount());
        return page;
    }

    public long getFeaturesCount() {
        return miscMapper.getFeaturesCount();
    }
}
