package com.mldong.common.base;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;
/**
 * 分页数据实体
 * @author mldong
 *
 * @param <T>
 */
public class CommonPage<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8297224397945376979L;
	/**
	 * 每几页
	 */
	private int pageNum;
	/**
	 * 每页大小
	 */
    private int pageSize;
    /**
     * 总数
     */
    private long recordCount;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 数据集合
     */
    private List<T> rows;
    /**
     * 将mybatis分页插件实体转成通用分页数据实体
     * @param page
     * @return
     */
    public static <T> CommonPage<T> toPage(Page<T> page) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(page.getPages());
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setRecordCount(page.getTotal());
        result.setRows(page.getResult());
        return result;
    }

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
    
    
}
