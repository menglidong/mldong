package com.mldong.base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel
public class CommonPage<T> {
    /**
     * 默认分页彩虹展示数量
     */
    public static final int RAINBOW_NUM = 5;
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
     * 分页彩虹
     */
    private int[] rainbow;
    /**
     * 扩展数据（便于添加除了分页相关之外的数据，如有些tab的待处理数、已完成数等）
     */
    private Dict ext;
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
    /**
     * 将mybatis分页插件实体转成通用分页数据实体
     * @param page
     * @return
     */
    public static <T> CommonPage<T> toPage(IPage<T> page, IRowHandler<T> rowHandler) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(Long.valueOf(page.getPages()).intValue());
        result.setPageNum(Long.valueOf(page.getCurrent()).intValue());
        result.setPageSize(Long.valueOf(page.getSize()).intValue());
        result.setRecordCount(Long.valueOf(page.getTotal()).intValue());
        result.setRows(page.getRecords());
        if(rowHandler!=null) {
            result.getRows().forEach(row->{
                rowHandler.handle(row);
            });
        }
        result.setRainbow(PageUtil.rainbow(Convert.toInt(page.getCurrent()),
                Convert.toInt(result.getTotalPage()), RAINBOW_NUM));
        return result;
    }
    /**
     * 将mybatis分页插件实体转成通用分页数据实体
     * @param page
     * @return
     */
    public static <M> CommonPage<M> toPage(IPage<?> page,Class<M> clazz,IRowHandler<M> rowHandler) {
        CommonPage<M> result = new CommonPage<M>();
        result.setTotalPage(Long.valueOf(page.getPages()).intValue());
        result.setPageNum(Long.valueOf(page.getCurrent()).intValue());
        result.setPageSize(Long.valueOf(page.getSize()).intValue());
        result.setRecordCount(Long.valueOf(page.getTotal()).intValue());
        result.setRows(new ArrayList<>());
        page.getRecords().forEach(item->{
            M obj = BeanUtil.toBean(item,clazz);
            if(rowHandler!=null) {
                rowHandler.handle(obj);
            }
            result.getRows().add(obj);
        });
        result.setRainbow(PageUtil.rainbow(Convert.toInt(page.getCurrent()),
                Convert.toInt(result.getTotalPage()), RAINBOW_NUM));
        return result;
    }
}
