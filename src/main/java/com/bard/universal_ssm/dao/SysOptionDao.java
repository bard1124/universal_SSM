package com.bard.universal_ssm.dao;

import com.bard.universal_ssm.model.po.SysOptionPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 配置表数据库访问对象
 * @author VBA Tool
 * @version 1.0
 *
 */
@CacheConfig(cacheNames = "option")
public interface SysOptionDao {
    /**
     * 获取所有数据
     * @return SysOptionPo对象列表
     */
    public List<SysOptionPo> selectAll();

    /**
     * 根据主键获取数据
     * @param id ID
     * @return SysOptionPo对象
     */
    public SysOptionPo selectOne(Integer id);

    /**
     * 插入数据
     * @param sysOptionPo SysOption对象
     * @return 插入条数
     */
    public Integer insert(SysOptionPo sysOptionPo);

    /**
     * 根据主键删除数据
     * @param id ID
     * @return 删除条数
     */
    public Integer delete(Integer id);

    /**
     * 更新数据
     * @param sysOptionPo SysOption对象
     * @return 更新条数
     */
    public Integer update(SysOptionPo sysOptionPo);

    /**
     * 获取pid获取数据
     * @param pid 父节点ID
     * @return SysOptionPo对象列表
     */
	@Cacheable(key = "#p0")
    public List<SysOptionPo> selectOptionByPid(Integer pid);
}
