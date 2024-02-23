package com.mldong.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import cn.xuyanwu.spring.file.storage.recorder.FileRecorder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.mldong.base.AntUploadModel;
import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.FileInfoPageParam;
import com.mldong.modules.sys.dto.FileInfoParam;
import com.mldong.modules.sys.entity.FileInfo;
import com.mldong.modules.sys.mapper.FileInfoMapper;
import com.mldong.modules.sys.service.FileInfoService;
import com.mldong.modules.sys.vo.FileInfoVO;
import com.mldong.util.UrlWrapUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * <p>
 * 文件信息 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2024-02-23
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService, FileRecorder {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(FileInfoParam param) {
        param.setId(null);
        FileInfo fileInfo = new FileInfo();
        BeanUtil.copyProperties(param, fileInfo);
        return super.save(fileInfo);
    }

    @Override
    public boolean update(FileInfoParam param) {
        FileInfo fileInfo = new FileInfo();
        BeanUtil.copyProperties(param, fileInfo);
        return super.updateById(fileInfo);
    }

    @Override
    public CommonPage<FileInfoVO> page(FileInfoPageParam param) {
        IPage<FileInfoVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<FileInfoVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public FileInfoVO findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<AntUploadModel> fileInfoToAntUploadModel(List<String> mIds) {
        // id1,id2,id3
        // id1;bizType1,id2;bizType2,id3;bizType3
        List<String> ids = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        mIds.forEach(id->{
            String idArr [] = id.split(";");
            ids.add(idArr[0]);
            if(idArr.length>1) {
                map.put(idArr[0],idArr[1]);
            }
        });
        List<FileInfo> list = baseMapper.selectBatchIds(ids);
        return list.stream().map(item->{
            AntUploadModel antUploadModel = new AntUploadModel();
            antUploadModel.setId(item.getId().toString());
            antUploadModel.setUid(item.getId().toString());
            antUploadModel.setName(item.getOriginalFilename());
            antUploadModel.setBizType(map.get(item.getId().toString()));
            antUploadModel.setUrl(UrlWrapUtil.wrap(item.getUrl()));
            antUploadModel.setStatus("done");
            return antUploadModel;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean record(cn.xuyanwu.spring.file.storage.FileInfo fileInfo) {
        FileInfo info = new FileInfo();
        BeanUtil.copyProperties(fileInfo, info,"id","attr");
        info.setAttr(JSONUtil.toJsonStr(fileInfo.getAttr()));
        info.setId(Convert.toLong(fileInfo.getObjectId()));
        //计算文件大小信息
        if(info.getSize()!=null) {
            info.setSizeInfo(FileUtil.readableFileSize(info.getSize()));
        }
        if(info.getThSize()!=null) {
            info.setThSizeInfo(FileUtil.readableFileSize(info.getThSize()));
        }
        return SqlHelper.retBool(baseMapper.insert(info));
    }

    @SneakyThrows
    @Override
    public cn.xuyanwu.spring.file.storage.FileInfo getByUrl(String url) {
        FileInfo info = baseMapper.selectOne(Wrappers.lambdaQuery(FileInfo.class).eq(FileInfo::getUrl,url));
        if(info!=null) {
            cn.xuyanwu.spring.file.storage.FileInfo fileInfo = new cn.xuyanwu.spring.file.storage.FileInfo();
            BeanUtil.copyProperties(fileInfo, info,"id","attr");
            fileInfo.setId(info.getId().toString());
            fileInfo.setAttr(JSONUtil.toBean(info.getAttr(), Dict.class));
            return fileInfo;
        }
        return null;
    }

    @Override
    public boolean delete(String url) {
        return SqlHelper.retBool(baseMapper.delete(Wrappers.lambdaQuery(FileInfo.class).eq(FileInfo::getUrl,url)));
    }
}
