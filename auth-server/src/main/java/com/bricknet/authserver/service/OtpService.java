package com.bricknet.authserver.service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class OtpService {
    private static final String FROM_EMAIL = "raipiyush886@gmail.com";
    private static final String EMAIL_PASSWORD = "oyyokcdnbhqycssk";

    private static Map<String, String> otpStorage = new HashMap<String, String>();

    public static String generateOTP(String email) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        String generatedOtp = Integer.toString(otp);
        storeOTP(email, generatedOtp);
        return generatedOtp;
    }
    public static void sendOTPEmail(String toEmail, String otp) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP for password reset: " + otp);
        Transport.send(message);
    }

    public boolean verifyOTP(String email, String otp) {
        // Retrieve the OTP for the given email from storage
        String storedOTP = otpStorage.get(email);

        // Check if the stored OTP matches the provided OTP
        return storedOTP != null && storedOTP.equals(otp);
    }

    // Method to store an OTP for a user (for testing purposes)
    public static void storeOTP(String email, String otp) {
        otpStorage.put(email, otp);
    }

    // Method to remove an OTP from storage (for testing purposes)
    public void removeOTP(String email) {
        otpStorage.remove(email);
    }

}
