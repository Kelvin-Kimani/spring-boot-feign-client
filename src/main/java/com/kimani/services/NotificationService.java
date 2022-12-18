package com.kimani.services;

import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.kimani.configs.AfricasTalkingConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */


@Service
public record NotificationService(SmsService smsService) implements INotificationService {


    @Override
    public List<Recipient> sendMessage(String[] to, String message) {
        try {
            return smsService.send(message, to, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
