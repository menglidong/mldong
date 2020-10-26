package com.mldong.modules.sys.service;

import java.util.List;

import com.mldong.common.base.CommonPage;
import com.mldong.common.upload.model.UploadResult;
import com.mldong.common.upload.model.UploadTokenVo;
import com.mldong.modules.sys.dto.SysUploadBizTypeParam;
import com.mldong.modules.sys.dto.SysUploadRecordPageParam;
import com.mldong.modules.sys.dto.SysUploadRecordParam;
import com.mldong.modules.sys.entity.SysUploadRecord;
/**
 * <p>业务接口层</p>
 * <p>上传记录</p>
 *
 * @since 2020-06-14 10:16:54
 */
public interface SysUploadRecordService{
	/**
	 * 添加上传记录
	 * @param param
	 * @return
	 */
	public int save(SysUploadRecordParam param);
	/**
	 * 更新上传记录
	 * @param param
	 * @return
	 */
	public int update(SysUploadRecordParam param);
	/**
	 * 删除上传记录
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);
	/**
	 * 查询上传记录
	 * @param id
	 * @return
	 */
	public SysUploadRecord get(Long id);
	/**
	 * 分页查询上传记录列表
	 * @param param	分页查询参数
	 * @return
	 */
	public CommonPage<SysUploadRecord> list(SysUploadRecordPageParam param);
	/**
	 * 创建上传凭证
	 * @param param
	 * @return
	 */
	public UploadTokenVo createUploadToken(SysUploadBizTypeParam param);
	/**
	 * 回调处理
	 * @param callbackAuthHeader 请求头
	 * @param callbackBody 请求正文
	 * @return
	 */
	public UploadResult handleCallback(String callbackAuthHeader, String callbackBody);

	/**
	 * 保存上传记录并返回主键
	 * @param uploadRecord
	 * @return
	 */
	public Long saveReturnId(SysUploadRecord uploadRecord);
}
