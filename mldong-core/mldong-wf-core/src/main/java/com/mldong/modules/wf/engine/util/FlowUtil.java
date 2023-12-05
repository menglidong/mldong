package com.mldong.modules.wf.engine.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mldong.modules.wf.api.WfUserApi;
import com.mldong.modules.wf.engine.core.ServiceContext;
import com.mldong.modules.wf.engine.model.ProcessModel;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.enums.FlowConst;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 流程工具
 * @author mldong
 * @date 2023/10/7
 */
public class FlowUtil {
    private FlowUtil(){}

    /**
     * 追加用户信息到变量中
     * @param operator
     * @param args
     */
    public static void addUserInfoToArgs(String operator, Dict args){
        WfUserApi wfUserApi = ServiceContext.find(WfUserApi.class);
        // 追加用户id
        args.put(FlowConst.USER_USER_ID,operator);
        // 追加用户姓名
        args.put(FlowConst.USER_REAL_NAME,wfUserApi.getRealName(operator));
        // 追加用户部门ID
        args.put(FlowConst.USER_DEPT_ID,wfUserApi.getDeptId(operator));
        // 追加用户部门名称
        args.put(FlowConst.USER_DEPT_NAME,wfUserApi.getDeptName(operator));
        // 追加用户岗位ID
        args.put(FlowConst.USER_POST_ID,wfUserApi.getPostId(operator));
        // 追加用户岗位名称
        args.put(FlowConst.USER_POST_NAME,wfUserApi.getPostName(operator));
    }


    /**
     * 增加自动构造标题
     * @param args
     */
    public static void addAutoGenTitle(String processDefineDisplayName,Dict args){
        // 申请人的xx流程-日期
        String format = "{}的{}-{}";
        args.put(FlowConst.AUTO_GEN_TITLE, StrUtil.format(format,args.getStr(FlowConst.USER_REAL_NAME),processDefineDisplayName,
                DateUtil.format(new Date(),"yyyy-MM-dd HH:hh")));
    }

    /**
     * 参数转字典
     * @param variable
     * @return
     */
    public static Dict variableToDict(String variable) {
        if(JSONUtil.isTypeJSON(variable)){
            return JSONUtil.toBean(variable,Dict.class);
        }
        return Dict.create();
    }

    /**
     * 判断是否为第一个任务节点
     * @param processModel
     * @param taskName
     * @return
     */
    public static boolean isFistTaskName(ProcessModel processModel, String taskName) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        processModel.getStart().getOutputs().forEach(nodeModel->{
            if(nodeModel.getTo().equalsIgnoreCase(taskName)) {
                atomicBoolean.set(true);
            }
        });
        return atomicBoolean.get();
    }

    /**
     * 解析日期
     * @param expireTime
     * @param args
     * @return
     */
    public static Date processTime(String expireTime,Dict args) {
        // 如果变量中存在，则使用变量中的时间
        if(args.containsKey(expireTime)) {
            Object obj = args.get(expireTime);
            if(obj instanceof Date) {
                return Convert.toDate(obj);
            } else if(obj instanceof Long) {
                return new Date(Convert.toLong(obj));
            } else if(obj instanceof String) {
                return DateUtil.parseDateTime(Convert.toStr(obj));
            }
        }
        if(StrUtil.isNotBlank(expireTime)) {
            if(expireTime.contains("s")){
                return DateUtil.offsetSecond(new Date(),Convert.toInt(expireTime.substring(0,expireTime.length()-1)));
            } else if(expireTime.contains("m")) {
                return DateUtil.offsetMinute(new Date(),Convert.toInt(expireTime.substring(0,expireTime.length()-1)));
            } else if(expireTime.contains("h")) {
                return DateUtil.offsetHour(new Date(),Convert.toInt(expireTime.substring(0,expireTime.length()-1)));
            } else if(expireTime.contains("d")) {
                return DateUtil.offsetDay(new Date(),Convert.toInt(expireTime.substring(0,expireTime.length()-1)));
            }
            return DateUtil.parseDateTime(expireTime);
        }
        return null;
    }
}
