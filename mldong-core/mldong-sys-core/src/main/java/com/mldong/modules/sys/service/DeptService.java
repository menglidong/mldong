package com.mldong.modules.sys.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.DeptPageParam;
import com.mldong.modules.sys.dto.DeptParam;
import com.mldong.modules.sys.vo.DeptVO;
import com.mldong.modules.sys.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
public interface DeptService extends IService<Dept> {
    /**
    * 添加部门
    * @param param
    * @return
    */
    boolean save(DeptParam param);

    /**
    * 更新部门
    * @param param
    * @return
    */
    boolean update(DeptParam param);

    /**
    * 自定义分页查询部门
    * @param param
    * @return
    */
    CommonPage<DeptVO> page(DeptPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    DeptVO findById(Long id);
    /**
    * 获取部门树
    * @param param
    * @return
    */
    List<DeptVO> tree(DeptPageParam param);

    /**
     * 从缓存中获取
     * @param id
     * @return
     */
    DeptVO getInCache(Long id);
}
