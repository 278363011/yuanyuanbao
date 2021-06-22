package com.codebaobao.sms;

import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsServiceImpl implements ISmsService{
    private final SmsProperties smsProperties;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public String sendSmsCode(String phone) {
        // redis缓存的键
        final String smsKey =SmsCodeUtil.createSmsCacheKey(smsProperties.getPhonePrefix(), phone,"wxUser_sign");
        //下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]  示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号
        String[] phoneNumbers = {"+86" + phone};
        //模板参数: 若无模板参数，则设置为空（第一个参数为随机验证码，第二个参数为有效时间）
        final String smsRandomCode = SmsCodeUtil.createSmsRandomCode(SmsLengthEnum.SMS_LENGTH_6);
        SendStatus[] sendStatuses = SmsUtil.sendSms(smsProperties, TemplateEnum.FIND_PASSWORD.getTemplateId(), new String[]{smsRandomCode}, phoneNumbers);
        String resCode = sendStatuses[0].getCode();
        if (resCode.equals(SmsResponseCodeEnum.OK.getCode())) {
            // 返回ok，缓存
            stringRedisTemplate.opsForValue().set(smsKey, smsRandomCode, 5, TimeUnit.MINUTES);
        } else {
            log.error("【短信业务-发送失败】phone:" + phone + "errMsg:" + sendStatuses[0].getMessage());
        }
        return smsRandomCode;
    }

    @Override
    public void sendSmsTemplate(String phone,String templateIdCode,String[] templateParams) {
        // redis缓存的键
        //下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]  示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号
        String[] phoneNumbers = {"+86" + phone};
        //模板参数: 若无模板参数，则设置为空（第一个参数为随机验证码，第二个参数为有效时间）
        SendStatus[] sendStatuses = SmsUtil.sendSms(smsProperties, templateIdCode, templateParams, phoneNumbers);
        String resCode = sendStatuses[0].getCode();
        if (resCode.equals(SmsResponseCodeEnum.OK.getCode())) {
            // 返回ok，缓存
            log.info("短信发送成功" );
        } else {
            log.error("【短信业务-发送失败】phone:" + phone + "errMsg:" + sendStatuses[0].getMessage());
        }
    }


    @Override
    public boolean verifySmsCode(String phone, String code) {
        // redis缓存的键
        final String smsKey = SmsCodeUtil.createSmsCacheKey(smsProperties.getPhonePrefix(), phone,
                "wxUser_sign");
         //如果key存在（存在并且未过期）
        if (stringRedisTemplate.hasKey(smsKey)) {
            String cacheCode = stringRedisTemplate.opsForValue().get(smsKey);
            if (cacheCode.equals(code)) {
                //验证码正确 删除验证码缓存
                stringRedisTemplate.delete(smsKey);
                log.info("【短信业务-微信公众号手机认证成功】phone：" + phone);
                return true;
            }
        }
        log.error("【短信业务-微信公众号手机认证失败】验证码错误。phone：" + phone);
        return false;
    }
}
