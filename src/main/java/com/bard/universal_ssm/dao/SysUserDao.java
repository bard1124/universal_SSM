package com.bard.universal_ssm.dao;

import com.bard.universal_ssm.model.dto.RoleDto;
import com.bard.universal_ssm.model.po.SysUserPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户表数据库访问对象
 * @author VBA Tool
 * @version 1.0
 *
 */
@Component
public interface SysUserDao {
    /**
     * 获取所有数据
     * @return SysUserPo对象列表
     */
    public List<SysUserPo> selectAll();

    /**
     * 根据主键获取数据
     * @param id 用户ID
     * @return SysUserPo对象
     */
    public SysUserPo selectOne(Integer id);

    /**
     * 插入数据
     * @param sysUserPo SysUser对象
     * @return 插入条数
     */
    public Integer insert(SysUserPo sysUserPo);

    /**
     * 根据主键删除数据
     * @param id 用户ID
     * @return 删除条数
     */
    public Integer delete(Integer id);

    /**
     * 更新数据
     * @param sysUserPo SysUser对象
     * @return 更新条数
     */
    public Integer update(SysUserPo sysUserPo);

    /**
     * 根据关键词获取所有数据
     * @param key 关键词
     * @return SysUserPo对象列表
     */
    public List<SysUserPo> selectAllByKey(String key);

    /**
     * 根据account获取用户
     * @param account
     * @return SysUserPo对象
     */
    public SysUserPo selectUserByAccount(String account);

    List<RoleDto> selectUserRole(Integer id);

    int deleteUserRole(Integer id);

    int insertUserRole(@Param("id") Integer id, @Param("list") List<Integer> list);
}
