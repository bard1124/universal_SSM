package com.bard.universal_ssm.dao;

import com.bard.universal_ssm.model.po.SysDprtmentPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 部门表数据库访问对象
 * @author VBA Tool
 * @version 1.0
 *
 */
@CacheConfig(cacheNames = "dprtment")
public interface SysDprtmentDao {
    /**
     * 获取所有数据
     * @return SysDprtmentPo对象列表
     */
    public List<SysDprtmentPo> selectAll();

    /**
     * 根据主键获取数据
     * @param id 部门ID
     * @return SysDprtmentPo对象
     */
	@Cacheable(key = "#p0")
    public SysDprtmentPo selectOne(Integer id);

    /**
     * 插入数据
     * @param sysDprtmentPo SysDprtment对象
     * @return 插入条数
     */
    public Integer insert(SysDprtmentPo sysDprtmentPo);

    /**
     * 根据主键删除数据
     * @param id 部门ID
     * @return 删除条数
     */
    public Integer delete(Integer id);

    /**
     * 更新数据
     * @param sysDprtmentPo SysDprtment对象
     * @return 更新条数
     */
    public Integer update(SysDprtmentPo sysDprtmentPo);

}
