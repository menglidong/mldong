package com.mldong.modules.sys.service;

import com.mldong.base.CommonPage;
import com.mldong.modules.sys.dto.VisLogPageParam;
import com.mldong.modules.sys.dto.VisLogParam;
import com.mldong.modules.sys.enums.VisTypeEnum;
import com.mldong.modules.sys.vo.VisLogVO;
import com.mldong.modules.sys.entity.VisLog;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 系统访问日志表 服务类
 * </p>
 *
 * @author mldong
 * @since 2024-02-06
 */
public interface VisLogService extends IService<VisLog> {
    /**
    * 添加系统访问日志表
    * @param param
    * @return
    */
    boolean save(VisLogParam param);

    /**
    * 更新系统访问日志表
    * @param param
    * @return
    */
    boolean update(VisLogParam param);

    /**
    * 自定义分页查询系统访问日志表
    * @param param
    * @return
    */
    CommonPage<VisLogVO> page(VisLogPageParam param);
    /**
    * 通过id查询
    * @param id
    * @return
    */
    VisLogVO findById(Long id);

    /**
     * 保存访问日志
     * @param vitType
     * @param account
     * @param success
     * @param message
     * @return
     */
    boolean saveVisLog(VisTypeEnum vitType, String account, String success, String message);
}
