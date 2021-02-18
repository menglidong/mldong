package com.mldong.common.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mldong.common.tool.StringTool;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询基类
 * @author mldong
 *
 * @param <T>
 */
public class PageParam<T> {
	/**
	 * 每几页
	 */
	@ApiModelProperty(value="每几页")
	private int pageNum;
	/**
	 * 每页大小
	 */
	@ApiModelProperty(value="每页大小")
    private int pageSize;
	public int getPageNum() {
		return pageNum;
	}
	@ApiModelProperty(value="关键字")
	private String keyworks;
	@ApiModelProperty(value="排序字段")
	private String orderBy;
	@ApiModelProperty(value="自定义查询参数集合")
	private List<WhereParam> whereParams;
	@ApiModelProperty(value="是否查询总条数")
	private YesNoEnum isCount;
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyworks() {
		return keyworks;
	}

	public void setKeyworks(String keyworks) {
		this.keyworks = keyworks;
	}

	public List<WhereParam> getWhereParams() {
		return whereParams;
	}

	public void setWhereParams(List<WhereParam> whereParams) {
		this.whereParams = whereParams;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public <T> Page<T> buildPage() {
        return buildPage(false);
    }

    public <T> Page<T> buildPage(boolean count) {
    	if(this.pageNum == 0) {
    		this.pageNum = 1;
    	}
    	if(this.pageSize==0) {
    		this.pageSize=15;
    	}
    	// 如果为否，则不查询总条件
    	if(YesNoEnum.NO.equals(isCount)){
    		count = false;
		}
        Page<T> page = PageHelper.startPage(this.pageNum, this.pageSize, count);
    	if(StringTool.isNotEmpty(orderBy)) {
    		orderBy = StringTool.humpToLine(orderBy);
    		if(StringTool.checkOrderBy(orderBy)) {
				page.setOrderBy(orderBy);
			}
		}
    	return page;
    }

	/**
	 * 新加条件
	 * @param tableAlias
	 * @param operateType
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
    public PageParam addCondition(String tableAlias,OperateTypeEnum operateType,String propertyName, Object propertyValue) {
		if(this.whereParams == null || this.whereParams.isEmpty()) {
			this.whereParams = new ArrayList<>();
		}
		WhereParam whereParam = this.whereParams.stream().filter(item->{return item.getPropertyName().equals(propertyName);}).findFirst().orElse(new WhereParam());
		if(whereParam.getPropertyName() == null) {
			whereParam.setTableAlias(tableAlias);
			whereParam.setOperateType(operateType);
			whereParam.setPropertyName(propertyName);
			whereParam.setPropertyValue(propertyValue);
			this.whereParams.add(whereParam);
		} else {
			whereParam.setTableAlias(tableAlias);
			whereParam.setOperateType(operateType);
			whereParam.setPropertyName(propertyName);
			whereParam.setPropertyValue(propertyValue);
		}
		return this;
	}

	/**
	 * 新加条件-追加在前面
	 * @param tableAlias
	 * @param operateType
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public PageParam addConditionToPre(String tableAlias,OperateTypeEnum operateType,String propertyName, Object propertyValue) {
		if(this.whereParams == null || this.whereParams.isEmpty()) {
			this.whereParams = new ArrayList<>();
		}
		WhereParam whereParam = this.whereParams.stream().filter(item->{return item.getPropertyName().equals(propertyName);}).findFirst().orElse(new WhereParam());
		if(whereParam.getPropertyName() == null) {
			whereParam.setTableAlias(tableAlias);
			whereParam.setOperateType(operateType);
			whereParam.setPropertyName(propertyName);
			whereParam.setPropertyValue(propertyValue);
			this.whereParams.add(0, whereParam);
		} else {
			whereParam.setTableAlias(tableAlias);
			whereParam.setOperateType(operateType);
			whereParam.setPropertyName(propertyName);
			whereParam.setPropertyValue(propertyValue);
			this.whereParams.remove(whereParam);
			this.whereParams.add(0, whereParam);
		}
		return this;
	}

	/**
	 * 等值条件-目前只是为了覆盖前端值，如userId等情况
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public PageParam addEqualsTo(String propertyName, Object propertyValue) {
    	return addCondition(null, OperateTypeEnum.EQ, propertyName, propertyValue);
	}
	/**
	 * 等值条件-目前只是为了覆盖前端值，如userId等情况
	 * @param tableAlias
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public PageParam addEqualsTo(String tableAlias,String propertyName, Object propertyValue) {
		return addCondition(tableAlias, OperateTypeEnum.EQ, propertyName, propertyValue);
	}
	/**
	 * 等值条件-目前只是为了覆盖前端值，如userId等情况
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public PageParam addEqualsToPre(String propertyName, Object propertyValue) {
		return addConditionToPre(null, OperateTypeEnum.EQ, propertyName, propertyValue);
	}

	/**
	 * 条件初始化
	 * @return
	 */
	public PageParam init() {
		this.whereParams = new ArrayList<>();
		return this;
	}
	/**
	 * 等值条件-目前只是为了覆盖前端值，如userId等情况
	 * @param tableAlias
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public PageParam addEqualsToPre(String tableAlias,String propertyName, Object propertyValue) {
		return addConditionToPre(tableAlias, OperateTypeEnum.EQ, propertyName, propertyValue);
	}
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public YesNoEnum getIsCount() {
		return isCount;
	}

	public void setIsCount(YesNoEnum isCount) {
		this.isCount = isCount;
	}
}
