package com.bard.universal_ssm.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MenuTreePo {

    private int id;// 节点id
    private String title;// 文本
    private String eng; // 英文文本
    private int pid; // 父节点id
    private Boolean checked=false; // checked
    private Integer type;
    //	private String isInsert;//是否有新增权限
//	private String isModify;//是否有修改权限
//	private String isDelete;//是否有删除权限
    private List<MenuTreePo> children = new ArrayList<MenuTreePo>();




}
