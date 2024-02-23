package com.mldong.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mldong.base.AntUploadModel;
import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.FileInfoPageParam;
import com.mldong.modules.sys.dto.FileInfoParam;
import com.mldong.modules.sys.entity.FileInfo;
import com.mldong.modules.sys.vo.FileInfoVO;

import java.util.List;

/**
 * <p>
 * 文件信息 服务类
 * </p>
 *
 * @author mldong
 * @since 2024-02-23
 */
public interface FileInfoService extends IService<FileInfo> {
    /**
    * 添加文件信息
    * @param param
    * @return
    */
    boolean save(FileInfoParam param);

    /**
    * 更新文件信息
    * @param param
    * @return
    */
    boolean update(FileInfoParam param);

    /**
    * 自定义分页查询文件信息
    * @param param
    * @return
    */
    CommonPage<FileInfoVO> page(FileInfoPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    FileInfoVO findById(Long id);
    /**
     * 文件信息转成文件对象
     * @param ids
     * @return
     */
    List<AntUploadModel> fileInfoToAntUploadModel(List<String> ids);
}
