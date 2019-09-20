package com.goods.service.ow.impl;

import com.goods.mapper.ow.SysParameterMapper;
import com.goods.service.ow.SysParameterService;
import com.goods.common.sys.SysParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname SysParameterServiceImpl
 * @Description TODO
 * @Date 2019/9/15 17:58
 * @Created by andy
 */
@Slf4j
@Service
public class SysParameterServiceImpl implements SysParameterService {
    @Autowired
    private SysParameterMapper sysParameterMapper;

    @Override
    public List<SysParameter> findAll() {
        List<SysParameter> sysParameters = sysParameterMapper.selectAll();
        return null;
    }
}
