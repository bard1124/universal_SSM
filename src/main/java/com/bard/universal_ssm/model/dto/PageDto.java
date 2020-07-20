package com.bard.universal_ssm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PageDto {

    //分页大小，每页记录数  0
	@JsonIgnore
    private Integer pageSize = 0;
    //分页开始，页码  0
	@JsonIgnore
    private Integer pageNum = 0;
}