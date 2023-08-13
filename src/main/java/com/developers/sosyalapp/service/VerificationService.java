package com.developers.sosyalapp.service;

import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.model.Account;
import com.developers.sosyalapp.model.VerifyEmail;
import com.developers.sosyalapp.repository.AccountRepository;
import com.developers.sosyalapp.repository.VerifyMailRepository;
import com.developers.sosyalapp.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
    private final VerifyMailRepository verifyMailRepository;
    private final AccountRepository accountRepository;

    public VerificationService(VerifyMailRepository verifyMailRepository, AccountRepository accountRepository) {
        this.verifyMailRepository = verifyMailRepository;
        this.accountRepository = accountRepository;
    }

        public VerifyEmail createVerification(String email){
            try {
                // Create 32 long random string
                String verificationToken = RandomGenerator.generateRandomString(32);
                VerifyEmail verifyEmail = new VerifyEmail(email, verificationToken);
                return verifyMailRepository.save(verifyEmail);
            } catch (Exception e) {
                throw new RuntimeException("Error creating verification token: " + e.getMessage());
            }
        }

    public void deleteVerification(String email){
        try {
            VerifyEmail verifyEmail = verifyMailRepository.findByEmail(email);
            verifyMailRepository.delete(verifyEmail);
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
                return new ApiResponse(false, "Token geçerli değil.");
            }
        } catch (Exception e) {
            return new ApiResponse(false, "Email validasyonu sırasında bir hata oluştu.");
        }
    }

    public void verifyAccount(String email) {
        Account account = accountRepository.findByEmail(email);
        account.setVerified(true);
        accountRepository.save(account);
    }
}
