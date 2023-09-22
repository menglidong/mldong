package com.mldong.base;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel
public class CommonPage<T> {
    @ApiModelProperty(value = "记录总数")
    private int recordCount;
    @ApiModelProperty(value = "总页数")
    private int totalPage;
    @ApiModelProperty(value = "每页大小")
    private int pageSize;
    @ApiModelProperty(value = "当前页")
    private int pageNum;
    @ApiModelProperty(value = "数据")
    private List<T> rows;
    /**
     * 将mybatis分页实体转成通用分页数据实体
     * @param page
     * @return
     */
    public static <T> CommonPage<T> toPage(IPage<T> page) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(Long.valueOf(page.getPages()).intValue());
        result.setPageNum(Long.valueOf(page.getCurrent()).intValue());
        result.setPageSize(Long.valueOf(page.getSize()).intValue());
        result.setRecordCount(Long.valueOf(page.getTotal()).intValue());
        result.setRows(page.getRecords());
        return result;
    }
    /**
     * 将mybatis分页实体转成通用分页数据实体
     * @param page
     * @return
     */
    public static <M> CommonPage<M> toPage(IPage<?> page,Class<M> clazz) {
        CommonPage<M> result = new CommonPage<M>();
        result.setTotalPage(Long.valueOf(page.getPages()).intValue());
        result.setPageNum(Long.valueOf(page.getCurrent()).intValue());
        result.setPageSize(Long.valueOf(page.getSize()).intValue());
        result.setRecordCount(Long.valueOf(page.getTotal()).intValue());
        result.setRows(new ArrayList<>());
        page.getRecords().forEach(item->{
            result.getRows().add(BeanUtil.toBean(item,clazz));
        });
        return result;
    }
}
