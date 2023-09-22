package com.mldong.modules.sys.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.DictPageParam;
import com.mldong.modules.sys.dto.DictParam;
import com.mldong.modules.sys.vo.DictVO;
import com.mldong.modules.sys.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
public interface DictService extends IService<Dict> {
  /**
  * 添加字典
  * @param param
  * @return
  */
  boolean save(DictParam param);

  /**
  * 更新字典
  * @param param
  * @return
  */
  boolean update(DictParam param);

  /**
  * 自定义分页查询字典
  * @param param
  * @return
  */
  CommonPage<DictVO> page(DictPageParam param);
  /**
  * 通过id查询
  * @param id
  * @return
  */
  DictVO findById(Long id);
  /**
   * 获取字典下拉
   *
   * @param dictType
   * @return
   */
  public List<cn.hutool.core.lang.Dict> getByDictType(String dictType);
}
