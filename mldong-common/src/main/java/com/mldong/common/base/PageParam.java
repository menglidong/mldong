package com.mldong.common.base;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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
	@ApiModelProperty(value="自定义查询参数集合")
	private List<WhereParam> whereParams;
	
	
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
        return PageHelper.startPage(this.pageNum, this.pageSize, count);
    }
    
}
