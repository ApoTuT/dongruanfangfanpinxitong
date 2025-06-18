package com.ysu.drffpjcxt.auth;

import com.ysu.drffpjcxt.controller.AuthController;
import com.ysu.drffpjcxt.entity.dto.auth.PasswordResetRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class LoginTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    @Autowired
    private AuthController authController;

    @Test
    public void ResetPassword() {
        PasswordResetRequest resetRequest = new PasswordResetRequest();
        resetRequest.setPhone("13931178989");
        resetRequest.setNewPassword("1234567");

        ResponseEntity<String> sendCodeResponse = authController.sendCode(resetRequest.getPhone());

        String responseBody = sendCodeResponse.getBody();

        resetRequest.setCode(responseBody);

        ResponseEntity<String> resetPasswordResponse = authController.resetPassword(resetRequest);
        log.info("Yes, reset password successfully: {}", resetPasswordResponse.getBody());
    }
}
