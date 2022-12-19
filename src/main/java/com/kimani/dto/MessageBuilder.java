package com.kimani.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Since 19/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */
@Data
@NoArgsConstructor
public class MessageBuilder {
    private String phoneNumber;
    private String message;

    public MessageBuilder(Customer customer) {
        setPhoneNumber(String.format("+%s", customer.getPhoneNumber()));
        setMessage(String.format("Good Morning %s. If you are receiving this, just know my Nats Server has worked. How? I might not know but it just did. A step closer to victory. 😉", customer.getName()));
    }
}
