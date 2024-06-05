package com.mldong.excel;

import java.util.List;

/**
 * 自定义导出服务-增加泛型
 * @author mldong
 * @date 2024/6/4
 */
public interface IMyExcelExportServer<T,M> {
    List<T> selectListForExcelExport(M pageParam, int pageNum);
}
