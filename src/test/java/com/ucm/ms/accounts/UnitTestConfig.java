package com.ucm.ms.accounts;

import com.ucm.lib.dao.UserDAO;
import com.ucm.lib.services.JwtUtil;
import com.ucm.ms.accounts.dao.AccountDAO;
import com.ucm.ms.accounts.dao.UserAccountConfirmationDAO;
import com.ucm.ms.accounts.dao.UserAccountDAO;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

/**
 * Configure external mocks.
 */
@Profile("test")
@Configuration
public class UnitTestConfig {
    @Bean
    @Primary
    public JwtUtil jwtUtil() {
        return Mockito.mock(JwtUtil.class);
    }
}
