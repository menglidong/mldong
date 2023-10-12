package com.mldong.modules.sys.service;

import cn.hutool.core.lang.Dict;
import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.DictItemPageParam;
import com.mldong.modules.sys.dto.DictItemParam;
import com.mldong.modules.sys.enums.DictDataType;
import com.mldong.modules.sys.vo.DictItemVO;
import com.mldong.modules.sys.entity.DictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典项 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
public interface DictItemService extends IService<DictItem> {
  /**
  * 添加字典项
  * @param param
  * @return
  */
  boolean save(DictItemParam param);

  /**
  * 更新字典项
  * @param param
  * @return
  */
  boolean update(DictItemParam param);

  /**
  * 自定义分页查询字典项
  * @param param
  * @return
  */
  CommonPage<DictItemVO> page(DictItemPageParam param);
  /**
  * 通过id查询
  * @param id
  * @return
  */
  DictItemVO findById(Long id);
  /**
   * 获取字段下拉项
   * @param dictId
   * @param dictDataType
   * @return
   */
  List<Dict> getDictItemListByDictId(Long dictId, DictDataType dictDataType);
}
