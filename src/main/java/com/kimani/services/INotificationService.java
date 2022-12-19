package com.kimani.services;

import com.africastalking.sms.Recipient;

import java.util.List;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */


public interface INotificationService {
    List<Recipient> sendMessage(String[] to, String message) throws Exception;

    void bulkPublishMessage();
}
