package com.mldong.generator.core;

import java.sql.Connection;
import java.util.List;

import com.mldong.generator.core.model.Column;
import com.mldong.generator.core.model.Table;

/**
 * 数据库元数据相关接口
 * @author mldong
 *
 */
public interface DataBase {
	/**
	 * 获取数据库连接对象
	 * @return 数据库连接对象
	 */
	public Connection getConnection();
	/**
	 * 关闭数据库连接对象
	 */
	public void closeConnection();
	/**
	 * 获取所有表对象
	 * @param tableNamePattern (sys_% 所有以sys_开头的表 )
	 * @return 表对象实例
	 */
	public List<Table> getTables(String tableNamePattern);
	/**
	 * 获取所有的表名
	 * @param tableNamePattern
	 * @return
	 */
	public List<String> getTableNames(String tableNamePattern);
	 /**
     * 根据表名获取数据表对象
     *
     * @param tableName 表名
     * @return 表对象实例
     */
	public Table getTable(String tableName);
	/**
	 * 获取所有表内的全部列表
	 * @param tableName 表名
	 * @return 数据列对象
	 */
	public List<Column> getColumns(String tableName);
	/**
	 * 获取所有表所有主键表
	 * @param tableName 表名
	 * @return
	 */
	public List<Column> getPrimaryKeys(String tableName);

    /**
     * 获取表备注信息
     *
     * @param tableName 表名
     * @return 表备注信息
     */
    public String getTableComment(String tableName);
}
