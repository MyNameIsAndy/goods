package com.goods.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.mapper.goods.GoodsOrderMapper;
import com.goods.model.DataGrid;
import com.goods.order.TbItem;
import com.goods.service.goods.GoodsService;
import com.goods.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname GoodsServiceImpl
 * @Description 商品
 * @Date 2019/9/15 15:21
 * @Created by andy
 */
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsOrderMapper goodsMapper;
    @Override
    public DataGrid findPage(Integer page, Integer rows) {
        List<TbItem> tbItems = new ArrayList<>();
        try{
            PageHelper.startPage(page,rows);
            tbItems = goodsMapper.selectAll();
            PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItems);
            log.info("商品查询信息总数【{}】",tbItems.size());
            return ResultUtil.data(pageInfo.getTotal(),pageInfo.getList());
        }catch (Exception e){
            log.error("商品查询异常",e);
            return ResultUtil.data(0,tbItems);
        }
    }
}
