package com.bard.universal_ssm.model.dto;

import lombok.Data;

@Data
public class RoleDto {
    private Integer id;
    private String roleName;
    private String roleDesc;
    private Integer status;
}
