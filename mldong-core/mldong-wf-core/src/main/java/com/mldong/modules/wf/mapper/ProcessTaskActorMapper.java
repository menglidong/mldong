package com.mldong.modules.wf.mapper;

import com.mldong.modules.wf.entity.ProcessTaskActor;
import com.mldong.modules.wf.vo.ProcessTaskActorVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 流程任务和参与人关系 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Mapper
public interface ProcessTaskActorMapper extends BaseMapper<ProcessTaskActor> {
    List<ProcessTaskActorVO> selectCustom(IPage<ProcessTaskActorVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessTaskActor> wrapper);
    ProcessTaskActorVO findById(@Param("id") Long id);
}
