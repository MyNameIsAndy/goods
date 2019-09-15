package com.goods.util;


import com.goods.model.DataGrid;
import com.goods.model.Result;

import java.util.List;

/**
 * 接过工具类
 * @author gao
 *
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static DataGrid data(long sum, List list){
        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(sum);
        dataGrid.setRows(list);
        return dataGrid;
    }
}

