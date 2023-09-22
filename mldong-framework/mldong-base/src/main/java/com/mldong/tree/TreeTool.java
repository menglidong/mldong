package com.mldong.tree;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * 树型工具
 * @author mldong
 * @date 2023/09/23
 */
public class TreeTool {
    /**
     * 列表转树
     * @param list
     * @param parentId
     * @return
     */
    public static List<INode> listToTree(List<INode> list, Object parentId){
        return listToTree(list, parentId, INode.class);
    }
    /**
     * 列表转树
     * @param list
     * @param parentId
     * @return
     */
    public static <T extends INode> List<T> listToTree(List<T> list, Object parentId, Class<T> clazz){
        if(list == null || list.isEmpty()){
            return new ArrayList<>();
        }
        List<T> res = list.stream().filter(item->{
            return item.getParentId()!=null&&parentId!=null&&item.getParentId().equals(parentId);
        }).collect(Collectors.toList());
        if(!res.isEmpty()) {
            res.forEach(item->{
                item.getChildren().addAll(listToTree(list, item.getId(),clazz));
            });
        }
        return res;
    }

    /**
     * 递归树
     * @param treeData 树型结构数据
     * @param recursionTree 递归处理接口
     * @param res 递归返回值
     * @param <T>
     * @param <R>
     */
    public static <T extends INode,R> void recursion(List<T> treeData, IRecursionTree<T,R> recursionTree, R res) {
        if(CollectionUtil.isNotEmpty(treeData)) {
            treeData.forEach(data->{
                recursionTree.rowHandleBefore(data,res);
                recursion((List<T>) data.getChildren(),recursionTree,res);
                recursionTree.rowHandleAfter(data, res);
            });
        }
    }
}
