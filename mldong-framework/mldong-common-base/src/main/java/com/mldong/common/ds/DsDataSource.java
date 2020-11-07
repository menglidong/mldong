package com.mldong.common.ds;

import javax.sql.DataSource;

public interface DsDataSource {
    public DataSource getDataSource();
    public String getDataSourceType();
}
