package com.goods.common.check;

import com.goods.CRCUtil;
import com.goods.DateTimeUtil;
import com.goods.RandomUtil;
import com.goods.StringUtil;
import com.goods.bean.TbBase;
import com.goods.common.State;
import com.goods.common.vo.RequestBody;
import com.goods.common.vo.RequestInfo;
import com.goods.common.vo.ResponseInfo;
import com.goods.json.JsonUtil;
import com.goods.mapper.order.TbBaseMapper;
import com.goods.service.sys.SysSerParameterService;
import com.goods.service.sys.SysStateService;
import com.networknt.schema.ValidationMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Classname DataCheck
 * @Description 数据校验
 * @Date 2019/9/21 10:02
 * @Created by andy
 */
@Component
@Slf4j
public class DataCheck {

    @Autowired
    private SysStateService sysStateService;
    @Autowired
    private JsonUtil jsonUtil;
    @Autowired
    private CRCUtil crcUtil;
    @Autowired
    private SysSerParameterService sysSerParameterService;
    @Autowired
    private TbBaseMapper tbBaseMapper;

    /**
     * 校验并保存base
     * @param parameter
     * @param save
     * @return
     */
    public Map<String,String> dataCheckAndSaveBase(String parameter,boolean save){
        Set<ValidationMessage> validate ;
        Map<String,String> result = new HashMap<>();
        result.put("state",State.SUCCESS_CODE.getCode());
        result.put("messge",sysStateService.findMessageByCode(State.SUCCESS_CODE));
        result.put("serialNo","");

        try{
            validate = jsonUtil.validate(RequestInfo.class, parameter);
            if(null != validate || !validate.isEmpty()){
                return printError(validate,result);
            }
            RequestInfo requestInfo = jsonUtil.json2Object(parameter, RequestInfo.class);
            //数据
            String body = requestInfo.getBody();
            //验签
            String signed = requestInfo.getSigned();
            String encrypt = crcUtil.encrypt(body);
            if(!StringUtils.equals(signed,encrypt)){
                result.put("state",State.BUSINESS_DATA_INVALID.getCode());
                result.put("messge",sysStateService.findMessageByCode(State.BUSINESS_DATA_INVALID)+"，数据解密失败");
                return  result;
            }
            validate = jsonUtil.validate(RequestBody.class, body);
            if(!validate.isEmpty() || null != validate){
                return printError(validate,result);
            }
            RequestBody requestBody = jsonUtil.json2Object(body, RequestBody.class);
            //服务模块
            boolean b = sysSerParameterService.checkSerPar(requestBody.getReqTxCode());
            if(!b){
                result.put("state",State.BUSINESS_DATA_INVALID.getCode());
                result.put("messge",sysStateService.findMessageByCode(State.BUSINESS_DATA_INVALID)+"，未检测到相应服务");
                return  result;
            }
            //交互流水号 格式 时间YYYYMMDD_系统编号_随机数
            String serialNo = requestBody.getSerialNo();
            String[] split = serialNo.split("_");
            if(split.length!=3){
                result.put("state",State.BUSINESS_DATA_INVALID.getCode());
                result.put("messge",sysStateService.findMessageByCode(State.BUSINESS_DATA_INVALID)+"，交互流水号长度错误");
                return  result;
            }
            String date =  split[0];
            String token =  split[1];
            if(!StringUtils.equals(token,requestBody.getReqTxCode()) || !DateTimeUtil.isEffectiveDate(DateTimeUtil.getFormatStr(date, "yyyyMMdd", "yyyy-MM-dd"), DateTimeUtil.getPreviousDate(), DateTimeUtil.getNextDate())){
                result.put("state",State.BUSINESS_DATA_INVALID.getCode());
                result.put("messge",sysStateService.findMessageByCode(State.BUSINESS_DATA_INVALID)+"，交互流水号格式错误");
                return  result;
            }
            TbBase tbBase = new TbBase();
            tbBase.setCreateTime(DateTimeUtil.getCurrentTime(3));
            tbBase.setId(RandomUtil.get32UUID());
            tbBase.setReqTxCode(requestBody.getReqTxCode());
            tbBase.setSerialNo(serialNo);
            tbBase.setContent(requestBody.getContent());
            tbBaseMapper.insert(tbBase);
            result.put("baseId",tbBase.getId());
            result.put("serialNo",serialNo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            log.error("数据校验失败：",e);
            result.put("state",State.BUSINESS_DATA_INVALID.getCode());
            result.put("messge",sysStateService.findMessageByCode(State.BUSINESS_DATA_INVALID)+"，数据校验异常");
            return result;
        }
    }

    /**
     * 封装返回值
     * @param parameter
     * @param result
     * @return
     */
    private Map<String,String> printError(Set<ValidationMessage> parameter ,Map result){
        log.error("接口校验失败");
        for(ValidationMessage vm : parameter){
            log.error("数据校验失败："+vm.getMessage());
        }
        result.put("state",State.BUSINESS_DATA_INVALID.getCode());
        result.put("message",sysStateService.findMessageByCode(State.BUSINESS_DATA_INVALID)+","+parameter.iterator().next());
        return result;
    }
}
