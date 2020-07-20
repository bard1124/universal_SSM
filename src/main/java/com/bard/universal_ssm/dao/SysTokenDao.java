package com.bard.universal_ssm.dao;

import com.bard.universal_ssm.model.po.SysTokenPo;

import java.util.List;

/**
 * token表数据库访问对象
 * @author VBA Tool
 * @version 1.0
 *
 */
public interface SysTokenDao {
    /**
     * 获取所有数据
     * @return SysTokenPo对象列表
     */
    public List<SysTokenPo> selectAll();

    /**
     * 根据主键获取数据
     * @param id 编号
     * @return SysTokenPo对象
     */
    public SysTokenPo selectOne(Integer id);

    /**
     * 插入数据
     * @param sysTokenPo SysToken对象
     * @return 插入条数
     */
    public Integer insert(SysTokenPo sysTokenPo);

    /**
     * 根据主键删除数据
     * @param id 编号
     * @return 删除条数
     */
    public Integer delete(Integer id);

    /**
     * 更新数据
     * @param sysTokenPo SysToken对象
     * @return 更新条数
     */
    public Integer update(SysTokenPo sysTokenPo);
    
    /**
     * 根据用户id返回数据
     * @param userid 用户id
     * @return SysTokenPo对象列表
     */
    public List<SysTokenPo> selectTokenByUserid(Integer userid);

    /**
     * 根据token字符串返回token信息
     * @param token token字符串
     * @return SysTokenPo对象
     */
    public SysTokenPo selectToken(String token);
}
