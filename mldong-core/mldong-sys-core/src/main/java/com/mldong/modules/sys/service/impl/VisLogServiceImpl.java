package com.mldong.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.consts.CommonConstant;
import com.mldong.exception.ServiceException;
import com.mldong.modules.sys.dto.VisLogPageParam;
import com.mldong.modules.sys.dto.VisLogParam;
import com.mldong.modules.sys.enums.VisTypeEnum;
import com.mldong.modules.sys.vo.VisLogVO;
import com.mldong.util.HttpServletUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import com.mldong.modules.sys.entity.VisLog;
import com.mldong.modules.sys.mapper.VisLogMapper;
import com.mldong.modules.sys.service.VisLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 系统访问日志表 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2024-02-06
 */
@Service
public class VisLogServiceImpl extends ServiceImpl<VisLogMapper, VisLog> implements VisLogService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(VisLogParam param) {
        param.setId(null);
        VisLog visLog = new VisLog();
        BeanUtil.copyProperties(param, visLog);
        return super.save(visLog);
    }

    @Override
    public boolean update(VisLogParam param) {
        VisLog visLog = new VisLog();
        BeanUtil.copyProperties(param, visLog);
        return super.updateById(visLog);
    }

    @Override
    public CommonPage<VisLogVO> page(VisLogPageParam param) {
        if(StrUtil.isEmpty(param.getOrderBy())) {
            param.setOrderBy("id desc");
        }
        IPage<VisLogVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<VisLogVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public VisLogVO findById(Long id) {
        return baseMapper.findById(id);
    }


    @Override
    public boolean saveVisLog(VisTypeEnum vitType, String account, String success, String message) {
        VisLogParam param = genBaseSysVisLog();
        param.setVisType(vitType.getCode());
        param.setName(vitType.getMessage());
        param.setVisTime(new Date());
        param.setAccount(account);
        param.setSuccess(success);
        param.setMessage(message);
        VisLog visLog = BeanUtil.toBean(param, VisLog.class);
        return SqlHelper.retBool(baseMapper.insert(visLog));
    }

    /**
     * 构造访问日志基础参数
     * @return
     */
    private static VisLogParam genBaseSysVisLog() {
        VisLogParam param = new VisLogParam();
        HttpServletRequest request = HttpServletUtil.getRequest();
        if (ObjectUtil.isNotNull(request)) {
            String ip = ServletUtil.getClientIP(HttpServletUtil.getRequest());
            param.setIp(ip);
            String userAgentStr = HttpServletUtil.getRequest().getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
            if(userAgent!=null) {
                param.setBrowser(userAgent.getBrowser().getName());
                param.setOs(userAgent.getOs().getName());
            }
            return param;
        } else {
            throw new ServiceException(9999, "获取请求信息失败");
        }
    }

}
