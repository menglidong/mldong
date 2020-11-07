package com.mldong.controller;

import com.mldong.common.base.CommonResult;
import com.mldong.common.tool.StringTool;
import com.mldong.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
@Api(tags = "数据服务")
@RestController
public class DataController {
    @Autowired
    private DataService dataService;
    @ApiOperation(value = "自定义查询1")
    @PostMapping(value = "/ds/{tableName}")
    public CommonResult<?> list(@PathVariable("tableName") String tableName,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestBody Map<String, Object> map) throws IOException {
        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!StringTool.checkColumn(key)) {
                continue;
            }
            if(key.startsWith("pk_") && value!=null) {
                String pkPropertyName = key.substring(3, key.length());
                return CommonResult.success(dataService.get(tableName, pkPropertyName, value));
            }
        }
        return CommonResult.success(dataService.list(tableName, map, pageNum, pageSize));
    }
    @ApiOperation(value = "自定义查询2")
    @PostMapping(value = "/ds/{dbName}/{tableName}")
    public CommonResult<?> list2(@PathVariable("dbName") String dbName,
                                 @PathVariable("tableName") String tableName,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestBody Map<String, Object> map) throws IOException {
        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!StringTool.checkColumn(key)) {
                continue;
            }
            if(key.startsWith("pk_") && value!=null) {
                String pkPropertyName = key.substring(3, key.length());
                CommonResult<?> res =CommonResult.success(dataService.get(dbName, tableName, pkPropertyName, value));
                return  res;
            }
        }
        CommonResult<?> res =  CommonResult.success(dataService.list(dbName,tableName, map, pageNum, pageSize));
        return res;
    }

}
