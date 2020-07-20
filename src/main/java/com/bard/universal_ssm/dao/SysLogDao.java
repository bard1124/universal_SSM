package com.bard.universal_ssm.dao;

import com.bard.universal_ssm.model.po.SysLogPo;

import java.util.List;

/**
 * 日志表数据库访问对象
 * @author VBA Tool
 * @version 1.0
 *
 */
public interface SysLogDao {

    /**
     * 插入数据
     * @param sysLogPo SysLog对象
     * @return 插入条数
     */
    public Integer insert(SysLogPo sysLogPo);

}
