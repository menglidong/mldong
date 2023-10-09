package com.mldong.modules.wf.mapper;

import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.vo.ProcessTaskVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 * 流程任务 Mapper 接口
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Mapper
public interface ProcessTaskMapper extends BaseMapper<ProcessTask> {
    List<ProcessTaskVO> selectCustom(IPage<ProcessTaskVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessTask> wrapper);
    ProcessTaskVO findById(@Param("id") Long id);

    /**
     * 我的待办
     * @param page
     * @param wrapper
     * @return
     */
    List<ProcessTaskVO> selectTodoList(IPage<ProcessTaskVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessTask> wrapper);
    /**
     * 我的已办
     * @param page
     * @param wrapper
     * @return
     */
    List<ProcessTaskVO> selectDoneList(IPage<ProcessTaskVO> page, @Param(Constants.WRAPPER) Wrapper<ProcessTask> wrapper);

}
