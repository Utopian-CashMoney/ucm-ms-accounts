package com.ucm.ms.accounts.dao;
//package com.ss.ucm.ms.loans.dao;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.math.BigDecimal;
//import java.util.Collection;
//
//import javax.transaction.Transactional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.ss.ucm.ms.loans.entities.LoanType;
//
//@SpringBootTest
//@Transactional
//class LoanDAOTest {
//    @Autowired
//    private LoanTypeDAO loanTypeDAO;
//
//    /**
//     * @author Joshua Podhola
//     */
//    @Test
//    void findTop50ByOrderByIdTest() {
//        //Add more than 50 tests. Probably not efficient, but that's really not what I'm concerned about.
//        for(int i = 0; i != 60; i++) {
//            LoanType account = new LoanType(BigDecimal.valueOf(100), "LoanSomething", BigDecimal.valueOf(100), 12);
//            loanTypeDAO.save(account);
//        }
//        Collection<LoanType> loans = loanTypeDAO.findTop50ByOrderById();
//        assertEquals(50, loans.size());
//    }
//}