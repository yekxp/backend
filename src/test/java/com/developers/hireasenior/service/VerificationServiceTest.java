package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.model.Account;
import com.developers.hireasenior.model.VerifyEmail;
import com.developers.hireasenior.repository.AccountRepository;
import com.developers.hireasenior.repository.VerifyMailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

public class VerificationServiceTest {

    @Mock
    private VerifyMailRepository verifyMailRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private VerificationService verificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testCreateVerification() {
        String email = "test@example.com";
        String verificationToken = "randomToken";
        when(verifyMailRepository.save(any(VerifyEmail.class))).thenReturn(new VerifyEmail(email, verificationToken));

        VerifyEmail result = verificationService.createVerification(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(verificationToken, result.getToken());
    }




    @Test
    void testDeleteVerification() {
        String email = "test@example.com";
        VerifyEmail verifyEmail = new VerifyEmail(email, "randomToken");
        when(verifyMailRepository.findByEmail(email)).thenReturn(verifyEmail);

        verificationService.deleteVerification(email);

        verify(verifyMailRepository).delete(verifyEmail);
    }

    @Test
    void testVerifyEmail_Success() {
        String email = "test@example.com";
        String token = "randomToken";
        VerifyEmail verifyEmail = new VerifyEmail(email, token);
        when(verifyMailRepository.findByEmail(email)).thenReturn(verifyEmail);
        when(accountRepository.findByEmail(email)).thenReturn(java.util.Optional.of(new Account()));

        ApiResponse response = verificationService.verifyEmail(email, token);

        assertTrue(response.isSuccess());
        assertEquals("Email başarıyla doğrulandı.", response.getMessage());
    }

    @Test
    void testVerifyEmail_Failure_InvalidToken() {
        String email = "test@example.com";
        String token = "invalidToken";
        VerifyEmail verifyEmail = new VerifyEmail(email, "randomToken");
        when(verifyMailRepository.findByEmail(email)).thenReturn(verifyEmail);

        ApiResponse response = verificationService.verifyEmail(email, token);

        assertFalse(response.isSuccess());
        assertEquals("Token geçerli değil.", response.getMessage());
    }

    @Test
    void testVerifyEmail_Failure_Exception() {
        String email = "test@example.com";
        String token = "randomToken";
        when(verifyMailRepository.findByEmail(email)).thenThrow(new RuntimeException("Test Exception"));

        ApiResponse response = verificationService.verifyEmail(email, token);

        assertFalse(response.isSuccess());
        assertEquals("Email validasyonu sırasında bir hata oluştu.", response.getMessage());
    }
}

