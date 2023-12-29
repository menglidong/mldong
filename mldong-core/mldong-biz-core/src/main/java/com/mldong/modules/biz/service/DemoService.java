package com.mldong.modules.biz.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.biz.dto.DemoPageParam;
import com.mldong.modules.biz.dto.DemoParam;
import com.mldong.modules.biz.vo.DemoVO;
import com.mldong.modules.biz.entity.Demo;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 演示 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-12-28
 */
public interface DemoService extends IService<Demo> {
    /**
    * 添加演示
    * @param param
    * @return
    */
    boolean save(DemoParam param);

    /**
    * 更新演示
    * @param param
    * @return
    */
    boolean update(DemoParam param);

    /**
    * 自定义分页查询演示
    * @param param
    * @return
    */
    CommonPage<DemoVO> page(DemoPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    DemoVO findById(Long id);
}
