package com.example.javabackend.modules.user.DTO;

import java.time.LocalDateTime;

public class otpDTO {

        // Other fields and constructors

        private String otp;

        private LocalDateTime otpExpiryTime;

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public LocalDateTime getOtpExpiryTime() {
            return otpExpiryTime;
        }

        public void setOtpExpiryTime(LocalDateTime otpExpiryTime) {
            this.otpExpiryTime = otpExpiryTime;
        }
}
