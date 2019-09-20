package com.goods.common.vo;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goods.CRCUtil;
import com.goods.DateTimeUtil;
import com.goods.StringUtil;
import com.goods.common.State;
import com.goods.common.sys.SysState;
import com.goods.json.JsonUtil;
import com.goods.service.sys.SysStateService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
public class ResponseBody {
    private String time;
    private String state;
    private String message;
    private String serialNo;
    private Object content;


    private static ResponseBody responseBody;

    @Autowired
    @JsonIgnore
    private CRCUtil crcUtil;

    @Autowired
    @JsonIgnore
    private SysStateService sysStateService;

    @Autowired
    @JsonIgnore
    private JsonUtil jsonUtil;


    @PostConstruct
    private void init(){
        responseBody = this;
        responseBody.sysStateService = this.sysStateService;
        responseBody.crcUtil = this.crcUtil;
        responseBody.jsonUtil = this.jsonUtil;
    }

    /**
     * 成功
     * @param serialNo
     * @param comNo
     * @return
     */
    public static ResponseInfo success(String serialNo,String comNo){
        ResponseInfo responseInfo = new ResponseInfo();
        ResponseBody responseBody = new ResponseBody();
        responseBody.setState(State.SUCCESS_CODE.getCode());
        responseBody.setMessage(responseBody.sysStateService.findMessageByState(State.SUCCESS_CODE));
        responseBody.setSerialNo(serialNo);
        responseBody.setTime(DateTimeUtil.getCurrentDate(3));
        generateSigned(responseInfo,responseBody,comNo);
        return responseInfo;
    }

    /**
     * 成功
     * @param serialNo
     * @param content
     * @param comNo
     * @return
     */
    public static ResponseInfo success(String serialNo ,Object content ,String comNo){
        return getRespInfo(serialNo,State.SUCCESS_CODE,"",content,comNo);
    }

    /**
     * 失败
     * @param serialNo
     * @param comNo
     * @return
     */
    public static ResponseInfo error(String serialNo,String comNo,State state,String message){
        return getRespInfo(serialNo,state,message,"",comNo);
    }

    public static ResponseInfo error(String serialNo,String comNo,State state,String message,Object content){
        ResponseInfo responseInfo = new ResponseInfo();
        ResponseBody respBody = getRespBody(serialNo, state, message, content);
        generateSigned(responseInfo,respBody,comNo);
        return responseInfo;
    }

    private static ResponseBody getRespBody(String serialNo,State state,String message,Object content){
        ResponseBody responseBody = new ResponseBody();
        responseBody.setState(state.getCode());
        responseBody.setContent(content);
        responseBody.setSerialNo(serialNo);
        String msg = responseBody.sysStateService.findMessageByCode(state.getCode());
        if(StringUtil.isNotEmpty(message)){
            msg = msg +","+message;
        }
        responseBody.setMessage(msg);
        responseBody.setTime(DateTimeUtil.getCurrentDate(3));
        return responseBody;
    }
    private static ResponseInfo getRespInfo(String serialNo,State state,String message,Object content,String comNo){
        ResponseInfo responseInfo = new ResponseInfo();
        ResponseBody responseBody = new ResponseBody();
        responseBody.setTime(DateTimeUtil.getCurrentDate(3));
        responseBody.setState(state.getCode());
        String msg = responseBody.sysStateService.findMessageByCode(state.getCode());
        if(StringUtil.isNotEmpty(message)){
            msg =msg +","+message;
        }
        responseBody.setMessage(msg);
        responseBody.setSerialNo(serialNo);
        responseBody.setContent(content);
        generateSigned(responseInfo,responseBody,comNo);
        return responseInfo;
    }

    /**
     *
     * @param responseInfo 返回类
     * @param responseBody 内部类
     * @param comNo 系统编号
     */
    private static void generateSigned(ResponseInfo responseInfo,ResponseBody responseBody,String comNo){
        String json = responseBody.jsonUtil.object2Json(responseBody);
        responseInfo.setBody(json);
        if(StringUtil.isNotEmpty(comNo)){
            String encrypt = responseBody.crcUtil.encrypt(json);
            responseInfo.setSigned(encrypt);
        }

    }


}
