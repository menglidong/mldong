package com.mldong.modules.sys.excel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mldong.excel.IExcelColumnHandler;
import com.mldong.excel.MyExcelExportEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mldong
 * @date 2024/6/3
 */
@Component
@RequiredArgsConstructor
public class RelIdExcelColumnHandler implements IExcelColumnHandler {
    @Override
    public String toName(MyExcelExportEntity excelExportEntity, Object value) {
        Dict props = getComponentProps(excelExportEntity);
        String api = props.getStr("api");
        if(StrUtil.isEmpty(api)) return value.toString();
        // 默认api规则为/{moduleName}/{entity}/select,所以可以从规则中获取对应的服务
        // 例：/biz/demo/select ==> 服务名为demoService所以提取demo
        String [] apiArr = api.split("/");
        if(apiArr.length<3) return value.toString();
        String serviceName = apiArr[2]+"ServiceImpl";
        IService<?> service = SpringUtil.getBean(serviceName);
        Object object = null;
        List<String> valueList = new ArrayList<>();
        String valueArr [] = value.toString().split(",");
        String labelField = props.get("labelKey", "name");
        for (int i = 0; i < valueArr.length; i++) {
            object = service.getById((Convert.toLong(valueArr[i])));
            if(object!=null) {
                String v = StrUtil.toString(ReflectUtil.getFieldValue(object,StrUtil.toCamelCase(labelField)));
                valueList.add(v);
            }
        }
        return CollectionUtil.join(valueList,",");
    }

    @Override
    public String toValue(MyExcelExportEntity excelExportEntity, Object value) {
        Dict props = getComponentProps(excelExportEntity);
        String api = props.getStr("api");
        if(StrUtil.isEmpty(api)) return value.toString();
        // 默认api规则为/{moduleName}/{entity}/select,所以可以从规则中获取对应的服务
        // 例：/biz/demo/select ==> 服务名为demoService所以提取demo
        String [] apiArr = api.split("/");
        if(apiArr.length<3) return value.toString();
        String serviceName = apiArr[3]+"ServiceImpl";
        IService<?> service = SpringUtil.getBean(serviceName);
        Object object = null;
        List<String> valueList = new ArrayList<>();
        String valueArr [] = value.toString().split(",");
        String valueField = props.get("valueKey", "id");
        String labelField = props.get("labelKey", "name");
        for (int i = 0; i < valueArr.length; i++) {
            LambdaQueryWrapper lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(labelField,valueArr[i]);
            object = service.getOne(lambdaQueryWrapper);
            if(object!=null) {
                String v = StrUtil.toString(ReflectUtil.getFieldValue(object,StrUtil.toCamelCase(valueField)));
                valueList.add(v);
            }
        }
        return CollectionUtil.join(valueList,",");
    }

    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public String getMessage() {
        return "关联ID处理类";
    }
}
