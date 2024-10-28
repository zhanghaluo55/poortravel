package com.hongpro.poortravel.common.web.components.datatables;

import com.hongpro.poortravel.common.dto.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataTablesResult extends BaseResult implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private String error;
}
