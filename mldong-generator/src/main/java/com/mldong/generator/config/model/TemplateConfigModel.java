package com.mldong.generator.config.model;
/**
 * 模板配置实体
 * @author mldong
 *
 */
public class TemplateConfigModel {
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 是否选中
	 */
	private boolean selected;
	/**
	 * 是否选覆盖
	 */
	private boolean covered;
	/**
	 * 模板路径
	 */
	private String templateFile;
	/**
	 * 模板生成文件名
	 */
	private String targetFileName;
	/**
	 * 生成到的目录
	 */
	private String targetPath;
	/**
	 * 文件编码
	 */
	private String encoding;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isCovered() {
		return covered;
	}
	public void setCovered(boolean covered) {
		this.covered = covered;
	}
	public String getTemplateFile() {
		return templateFile;
	}
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}
	public String getTargetFileName() {
		return targetFileName;
	}
	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
}
