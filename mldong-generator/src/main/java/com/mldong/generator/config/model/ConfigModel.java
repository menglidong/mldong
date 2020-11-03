package com.mldong.generator.config.model;

/**
 * 配置模型
 * @author mldong
 *
 */
public class ConfigModel {

	/**
	 * 数据库配置模型
	 */
	private DbConfigModel database;
	/**
	 * 基础包名
	 */
	private String basePackage;
	/**
	 * 生成代码根目录
	 */
	private String targetProject;
	/**
	 * 模块名
	 */
	private String moduleName;
	/**
	 * 模块描述
	 */
	private String moduleDesc;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 表配置
	 */
	private TableConfigModel [] tables;
	/**
	 * 是否生成逻辑删除
	 */
	private boolean logicDelete;
	/**
	 * 模板配置
	 */
	private TemplateConfigModel [] templates;
	public DbConfigModel getDatabase() {
		return database;
	}
	public void setDatabase(DbConfigModel database) {
		this.database = database;
	}
	public String getBasePackage() {
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	public String getTargetProject() {
		return targetProject;
	}
	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public TableConfigModel[] getTables() {
		return tables;
	}
	public void setTables(TableConfigModel[] tables) {
		this.tables = tables;
	}
	public TemplateConfigModel[] getTemplates() {
		return templates;
	}
	public void setTemplates(TemplateConfigModel[] templates) {
		this.templates = templates;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isLogicDelete() {
		return logicDelete;
	}

	public void setLogicDelete(boolean logicDelete) {
		this.logicDelete = logicDelete;
	}
}
