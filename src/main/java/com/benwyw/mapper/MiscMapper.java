package com.benwyw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.benwyw.model.Feature;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MiscMapper extends BaseMapper<Feature> {
	List<Feature> getFeatures(IPage<Feature> page);
	long getFeaturesCount();
}
