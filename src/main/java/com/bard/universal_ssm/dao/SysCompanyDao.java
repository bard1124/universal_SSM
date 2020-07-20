package com.bard.universal_ssm.dao;

import com.bard.universal_ssm.model.po.SysCompanyPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 公司表数据库访问对象
 * @author VBA Tool
 * @version 1.0
 *
 */
@CacheConfig(cacheNames = "company")
public interface SysCompanyDao {
    /**
     * 获取所有数据
     * @return SysCompanyPo对象列表
     */
    public List<SysCompanyPo> selectAll();

    /**
     * 根据主键获取数据
     * @param id 公司ID
     * @return SysCompanyPo对象
     */
	@Cacheable(key = "#p0")
    public SysCompanyPo selectOne(Integer id);

    /**
     * 插入数据
     * @param sysCompanyPo SysCompany对象
     * @return 插入条数
     */
    public Integer insert(SysCompanyPo sysCompanyPo);

    /**
     * 根据主键删除数据
     * @param id 公司ID
     * @return 删除条数
     */
    public Integer delete(Integer id);

    /**
     * 更新数据
     * @param sysCompanyPo SysCompany对象
     * @return 更新条数
     */
    public Integer update(SysCompanyPo sysCompanyPo);

}
