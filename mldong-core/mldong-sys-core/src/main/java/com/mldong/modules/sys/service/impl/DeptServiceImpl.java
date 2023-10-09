package com.mldong.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mldong.base.CommonPage;
import com.mldong.base.YesNoEnum;
import com.mldong.modules.sys.dto.DeptPageParam;
import com.mldong.modules.sys.dto.DeptParam;
import com.mldong.modules.sys.vo.DeptVO;
import com.mldong.util.LowCodeServiceUtil;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.mldong.modules.sys.entity.Dept;
import com.mldong.modules.sys.mapper.DeptMapper;
import com.mldong.modules.sys.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.mldong.tree.TreeTool;
/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author mldong
 * @since 2023-09-21
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DeptParam param) {
        param.setId(null);
        LowCodeServiceUtil.checkUnique(baseMapper,"code",param.getCode(),null,"唯一编码已存在，请检查code参数");
        Dept dept = new Dept();
        BeanUtil.copyProperties(param, dept);
        return super.save(dept);
    }

    @Override
    public boolean update(DeptParam param) {
        Dept dept = new Dept();
        LowCodeServiceUtil.checkUnique(baseMapper,"code",param.getCode(),param.getId(),"唯一编码已存在，请检查code参数");
        BeanUtil.copyProperties(param, dept);
        return super.updateById(dept);
    }

    @Override
    public CommonPage<DeptVO> page(DeptPageParam param) {
        IPage<DeptVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("t.is_deleted",YesNoEnum.NO);
        List<DeptVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    @Override
    public DeptVO findById(Long id) {
        return baseMapper.findById(id);
    }
    @Override
    public List<DeptVO> tree(DeptPageParam param) {
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.orderByAsc("sort");
        List<Dept> list = this.list(queryWrapper);
        List<DeptVO> treeData = TreeTool.listToTree(BeanUtil.copyToList(list,DeptVO.class),0L,DeptVO.class);
        return treeData;
    }

    @Override
    public DeptVO getInCache(Long id) {
        return findById(id);
    }
}
