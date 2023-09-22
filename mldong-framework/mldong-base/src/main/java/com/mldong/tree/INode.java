package com.mldong.tree;

import java.util.List;
/**
 * 树型节点
 * @author mldong
 * @date 2023/09/23
 */
public interface INode  {
    public Object getId();
    public Object getParentId();
    public String getName();
    public List<INode> getChildren();
}
