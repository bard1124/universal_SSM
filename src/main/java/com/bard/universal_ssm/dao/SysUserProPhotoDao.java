package com.bard.universal_ssm.dao;

import com.bard.universal_ssm.model.po.SysUserProPhotoPo;

import java.util.List;

/**
 * 用户头像表数据库访问对象
 * @author VBA Tool
 * @version 1.0
 *
 */
public interface SysUserProPhotoDao {
    /**
     * 获取所有数据
     * @return SysUserProPhotoPo对象列表
     */
    public List<SysUserProPhotoPo> selectAll();

    /**
     * 根据主键获取数据
     * @param id ID
     * @return SysUserProPhotoPo对象
     */
    public SysUserProPhotoPo selectOne(Integer id);

    /**
     * 插入数据
     * @param sysUserProPhotoPo SysUserProPhoto对象
     * @return 插入条数
     */
    public Integer insert(SysUserProPhotoPo sysUserProPhotoPo);

    /**
     * 根据主键删除数据
     * @param id ID
     * @return 删除条数
     */
    public Integer delete(Integer id);

    /**
     * 更新数据
     * @param sysUserProPhotoPo SysUserProPhoto对象
     * @return 更新条数
     */
    public Integer update(SysUserProPhotoPo sysUserProPhotoPo);

    /**
     * 根据用户id删除数据
     * @param id 用户id
     * @return 删除条数
     */
	public Integer deleteByUserId(Integer id);

	/**
	 * 根据用户id获取数据
	 * @param id
	 * @return SysUserProPhotoPo对象列表
	 */
	public List<SysUserProPhotoPo> selectProPhotoByUserId(Integer id);

}
