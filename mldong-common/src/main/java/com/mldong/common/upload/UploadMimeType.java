package com.mldong.common.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 上传媒体类型与后辍转换
 * @author mldong
 *
 */
public class UploadMimeType {
	private static Map<String,String> map = new HashMap<>();
	private UploadMimeType() {}
	static{
		// 文档文件类型的
		map.put(".ai", "application/postscript");
		map.put(".eps", "application/postscript");
		map.put(".exe", "application/octet-stream");
		map.put(".doc", "application/vnd.ms-word");
		map.put(".xls", "application/vnd.ms-excel");
		map.put(".ppt", "application/vnd.ms-powerpoint");
		map.put(".pps", "application/vnd.ms-powerpoint");
		map.put(".pdf", "application/pdf");
		map.put(".xml", "application/xml");
		map.put(".odt", "application/vnd.oasis.opendocument.text");
		map.put(".swf", "application/x-shockwave-flash");
		// 压缩文件类型的
		map.put(".gz", "application/x-gzip");
		map.put(".tgz", "application/x-gzip");
		map.put(".bz", "application/x-bzip2");
		map.put(".bz2", "application/x-bzip2");
		map.put(".tbz", "application/x-bzip2");
		map.put(".zip", "application/zip");
		map.put(".rar", "application/x-rar");
		map.put(".tar", "application/x-tar");
		map.put(".7z", "application/x-7z-compressed");
		// 文字类型
		map.put(".txt", "text/plain");
		map.put(".php", "text/x-php");
		map.put(".html", "text/html");
		map.put(".htm", "text/html");
		map.put(".js", "text/javascript");
		map.put(".css", "text/css");
		map.put(".rtf", "text/rtf");
		map.put(".rtfd", "text/rtfd");
		map.put(".py", "text/x-python");
		map.put(".java", "text/x-java-source");
		map.put(".rb", "text/x-ruby");
		map.put(".sh", "text/x-shellscript");
		map.put(".pl", "text/x-perl");
		map.put(".sql", "text/x-sql");
		// 图片类型的
		map.put(".bmp", "image/x-ms-bmp");
		map.put(".jpg", "image/jpeg");
		map.put(".jpeg", "image/jpeg");
		map.put(".gif", "image/gif");
		map.put(".png", "image/png");
		map.put(".tif", "image/tiff");
		map.put(".tiff", "image/tiff");
		map.put(".tga", "image/x-targa");
		map.put(".psd", "image/vnd.adobe.photoshop");
		// 音频文件类型的
		map.put(".mp3", "audio/mpeg");
		map.put(".mid", "audio/midi");
		map.put(".ogg", "audio/ogg");
		map.put(".mp4a", "audio/mp4");
		map.put(".wav", "audio/wav");
		map.put(".wma", "audio/x-ms-wma");
		// 视频文件类型的
		map.put(".avi", "video/x-msvideo");
		map.put(".dv", "video/x-dv");
		map.put(".mp4", "video/mp4");
		map.put(".mpeg", "video/mpeg");
		map.put(".mpg", "video/mpeg");
		map.put(".mov", "video/quicktime");
		map.put(".wm", "video/x-ms-wmv");
		map.put(".flv", "video/x-flv");
		map.put(".mkv", "video/x-matroska");
	}
	/**
	 * 获取媒体类型
	 * @param ext
	 * @return
	 */
	public static String getMimeType(String ext) {
		return getMimeType(ext, ";");
	}
	/**
	 * 获取媒体类型
	 * @param ext 后辍(多个使用逗号分割)
	 * @param delimiter 拼接mimeType的分割符 image/png;image/jpeg
	 * @return
	 */
	public static String getMimeType(String ext,String delimiter) {
		String [] exts = ext.split(",");
		List<String> list = new ArrayList<>();
		for(String s: exts) {
			String mimeType = map.get(s);
			if(null != mimeType) {
				list.add(mimeType);
			}
		}
		return String.join(delimiter, list);
	}
}
