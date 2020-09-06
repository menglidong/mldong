package com.mldong.generator.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.mldong.common.enums.ErrorEnum;
import com.mldong.common.exception.GeneratorException;
import com.mldong.common.util.StringUtil;
import com.mldong.generator.config.GeneratorConfig;
import com.mldong.generator.config.model.ConfigModel;
import com.mldong.generator.core.DataBase;
import com.mldong.generator.core.model.CodedType;
import com.mldong.generator.core.model.Column;
import com.mldong.generator.core.model.Table;
/**
 * 元数据获取-mysql实现类
 * @author mldong
 *
 */
public class MysqlDataBase implements DataBase{
	/**
	 * 查询表名
	 */
	private final String SELECT_TABLE_NAME_SQL = "SELECT t.table_catalog,t.table_schema,t.table_name,table_type FROM information_schema.TABLES t where t.table_schema=? and t.table_name like ?";
	/**
	 * 查询表元数据
	 */
	private final String SELECT_TABLE_SQL = "SELECT t.table_catalog,t.table_schema,t.table_name,table_type FROM information_schema.TABLES t where t.table_schema=? and t.table_name = ?";
	/**
	 * 查询某个表的所有列
	 */
	private final String SELECT_TABLE_COLUMN_SQL = "SELECT t.table_schema,t.table_name,t.column_name,t.column_default, t.is_nullable,t.data_type,t.character_maximum_length,t.numeric_precision,t.numeric_scale,t.column_type,t.column_key, t.column_comment,t.extra FROM information_schema.columns t WHERE t.table_schema = ? AND t.table_name = ?";
	/**
	 * 查询表注释
	 */
	private final String SELECT_TABLE_REMARK_SQL = "show table status where NAME=?";
	private ConfigModel configModel;
	private Connection connection;
	private GeneratorConfig generatorConfig;
	public MysqlDataBase(GeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
		this.configModel = generatorConfig.getConfigModel();
	}
	@Override
	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				Connection conn = null;
				try {
					Class.forName(this.configModel.getDatabase().getDriverClass());
					conn = DriverManager.getConnection(
							this.configModel.getDatabase().getUrl(), 
							this.configModel.getDatabase().getUsername(), 
							this.configModel.getDatabase().getPassword());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void closeConnection() {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Table> getTables(String tableNamePattern) {
		List<String> tableNames = getTableNames(tableNamePattern);
		List<Table> tables = new ArrayList<Table>();
		for (String tableName : tableNames) {
			tables.add(getTable(tableName));
		}
		return tables;
	}

	@Override
	public List<String> getTableNames(String tableNamePattern) {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_NAME_SQL);
			ps.setString(1, connection.getCatalog());
			ps.setString(2, tableNamePattern);
			ResultSet rs = ps.executeQuery();
			List<String> tableNames = new ArrayList<String>();
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				tableNames.add(tableName);
			}
			return tableNames;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new GeneratorException(ErrorEnum.notGetTable);
	}

	@Override
	public Table getTable(String tableName) {
		Connection connection = getConnection();
		Table table = new Table();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_SQL);
			ps.setString(1, connection.getCatalog());
			ps.setString(2, tableName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				table.setTableName(tableName);
				table.setTableCameName(StringUtil.getCamelCaseString(tableName,
					false));
				table.setEntityName(StringUtil.getCamelCaseString(tableName,
						true));
				table.setCatalog(rs.getString("table_catalog"));
				table.setSchema(rs.getString("table_schema"));
				table.setTableType(rs.getString("TABLE_TYPE"));
				table.setRemark(getTableComment(tableName));
				table.setColumns(getColumns(tableName));
				table.setPrimaryKeys(getPrimaryKeys(tableName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	@Override
	public List<Column> getColumns(String tableName) {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_COLUMN_SQL);
			ps.setString(1, connection.getCatalog());
			ps.setString(2, tableName);
			ResultSet rs = ps.executeQuery();
			List<Column> columns = new ArrayList<Column>();
			while (rs.next()) {
				columns.add(getColumn(rs));
			}
			return columns;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new GeneratorException(ErrorEnum.notGetColumn);
	}
	/**
	 * 单个列详细信息
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Column getColumn(ResultSet rs) throws SQLException {
		try {
			Column column = new Column();
			// 列名
			String columnName = rs.getString("COLUMN_NAME");
			// 表名
			// String tableName = rs.getString("TABLE_NAME");
			column.setColumnName(columnName);
			long size = rs.getLong("CHARACTER_MAXIMUM_LENGTH");
			column.setSize(size==0L?rs.getLong("NUMERIC_PRECISION"):size);
			column.setNullable("YES".equals(rs.getString("IS_NULLABLE")));
			column.setDefaultValue(rs.getString("COLUMN_DEFAULT"));
			column.setDataType(rs.getString("DATA_TYPE"));
			column.setAutoincrement("auto_increment".equals(rs.getString("EXTRA")));
			column.setRemark(rs.getString("COLUMN_COMMENT"));
			column.setDecimalDigits(rs.getInt("NUMERIC_SCALE"));
			column.setJavaProperty(StringUtil.getCamelCaseString(columnName,
					false));
			String dataType = column.getDataType().toUpperCase();
			Map<String,String> map = generatorConfig.getDataType().get(dataType);
			if(null!=map) {
				column.setJavaType(map.get("javaType"));
				column.setFullJavaType(map.get("fullJavaType"));
			} else {
				column.setJavaType("String");
				column.setFullJavaType("java.lang.String");
			}
			String columnKey = rs.getString("COLUMN_KEY");
			column.setPrimaryKey("PRI".equals(columnKey));
			column.setForeignKey("MUL".equals(columnKey));
			if(column.getColumnName().startsWith("is_")) {
				column.setJavaType("YesNoEnum");
			} else {
				if(StringUtil.isNotEmpty(column.getRemark())) {
					// 不为空，判断是否为类型注解
					column.setCodedTypes(remarkToCodedType(column.getRemark()));
					if(!column.getCodedTypes().isEmpty()){
						column.setJavaType(StringUtil.getCamelCaseString(columnName,
							true)+"Enum");
					}
				}
			}
			return column;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Column> getPrimaryKeys(String tableName) {
		return getColumns(tableName).stream().filter(item->{
			return item.isPrimaryKey();
		}).collect(Collectors.toList());
	}

	@Override
	public String getTableComment(String tableName) {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_TABLE_REMARK_SQL);
			ps.setString(1, tableName);
			ResultSet rs = ps.executeQuery();
			String remark = "";
			if (rs.next()) {
				remark = rs.getString("COMMENT");
			}
			return remark;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new GeneratorException(ErrorEnum.notGetComment);
	}
	Pattern pattern = Pattern.compile("(\\d+)->(.+?)\\|([a-zA-Z_0-9]+?)[,)]");
	/**
	 * 注释转codedtype
	 * @param remark
	 * @return
	 */
	private List<CodedType> remarkToCodedType(String remark) {
		List<CodedType> list = new ArrayList<>();
		Matcher matcher = pattern.matcher(remark);
		while(matcher.find()) {
			list.add(new CodedType(Integer.parseInt(matcher.group(1)), matcher.group(2),matcher.group(3)));
		}
		return list;
	}
}
