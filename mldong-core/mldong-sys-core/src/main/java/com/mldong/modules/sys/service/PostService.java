package com.mldong.modules.sys.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.PostPageParam;
import com.mldong.modules.sys.dto.PostParam;
import com.mldong.modules.sys.vo.PostVO;
import com.mldong.modules.sys.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位 服务类
 * </p>
 *
 * @author mldong
 * @since 2023-09-20
 */
public interface PostService extends IService<Post> {
  /**
  * 添加岗位
  * @param param
  * @return
  */
  boolean save(PostParam param);

  /**
  * 更新岗位
  * @param param
  * @return
  */
  boolean update(PostParam param);

  /**
  * 自定义分页查询岗位
  * @param param
  * @return
  */
  CommonPage<PostVO> page(PostPageParam param);
  /**
  * 通过id查询
  * @param id
  * @return
  */
  PostVO findById(Long id);

  /**
   * 从缓存中获取
   * @param id
   * @return
   */
  PostVO getInCache(Long id);
}
