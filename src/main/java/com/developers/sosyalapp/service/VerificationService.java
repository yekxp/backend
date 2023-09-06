package com.developers.sosyalapp.service;

import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.model.Account;
import com.developers.sosyalapp.model.VerifyEmail;
import com.developers.sosyalapp.repository.AccountRepository;
import com.developers.sosyalapp.repository.VerifyMailRepository;
import com.developers.sosyalapp.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
    private final VerifyMailRepository verifyMailRepository;
    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(VerificationService.class);

    public VerificationService(VerifyMailRepository verifyMailRepository, AccountRepository accountRepository) {
        this.verifyMailRepository = verifyMailRepository;
        this.accountRepository = accountRepository;
    }
    public VerifyEmail createVerification(String email){
        try {
            // Create 32 long random string
            String verificationToken = RandomGenerator.generateRandomString(32);
            VerifyEmail verifyEmail = verifyMailRepository.save(new VerifyEmail(email, verificationToken));
            logger.info("Verification created successfully: {}", verifyEmail);
            return verifyEmail;
        } catch (Exception e) {
            logger.info("Error creating verification: {}", e.getMessage());
            throw new RuntimeException("Error creating verification token: " + e.getMessage());
        }
    }

    public void deleteVerification(String email){
        try {
            VerifyEmail verifyEmail = verifyMailRepository.findByEmail(email);
            verifyMailRepository.delete(verifyEmail);
            logger.info("Verification deleted successfully: {}", verifyEmail);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting verification token: " + e.getMessage());
        }
    }

    @Transactional
    public ApiResponse verifyEmail(String email, String token){
        try {
            VerifyEmail verifyEmail = verifyMailRepository.findByEmail(email);
            if(verifyEmail.getToken().equals(token)){
                verifyAccount(email);
                deleteVerification(email);
                return new ApiResponse(true, "Email başarıyla doğrulandı.");
            }else {
                logger.error("Verification failed, token is not valid.");
                return new ApiResponse(false, "Token geçerli değil.");
            }
        } catch (Exception e) {
            logger.error("Error verifying email: {}", e.getMessage());
            return new ApiResponse(false, "Email validasyonu sırasında bir hata oluştu.");
        }
    }

    public void verifyAccount(String email) {
        Account account = accountRepository.findByEmail(email);
        account.setVerified(true);
        accountRepository.save(account);
        logger.info("Account verified successfully: {}", account.getUsername());
    }
}
