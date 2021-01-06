package com.snapmine.SnapMineApi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLMapper<T> {

    T map(ResultSet resultSet) throws SQLException;

}
