package com.mldong.modules.wf.mapper;

import com.mldong.modules.wf.entity.ProcessCcInstance;
import com.mldong.modules.wf.vo.ProcessCcInstanceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 流程实例抄送 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-12-05
 */
@Mapper
public interface ProcessCcInstanceMapper extends BaseMapper<ProcessCcInstance> {
    List<ProcessCcInstanceVO> selectCustom(IPage<ProcessCcInstanceVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessCcInstance> wrapper);
    ProcessCcInstanceVO findById(@Param("id") Long id);
}
