package com.mldong.excel;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.lang.Dict;
import lombok.Data;

/**
 * 新的ExcelExportEntity实体类
 * @author mldong
 * @date 2024/6/3
 */
@Data
public class MyExcelExportEntity extends ExcelExportEntity {
    // 组件名称
    private String component;
    // 处理类型
    private String handlerType;
    // 处理参数
    private Dict componentProps = Dict.create();
}
