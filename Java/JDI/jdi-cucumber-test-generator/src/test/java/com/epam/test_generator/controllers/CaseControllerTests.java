package com.epam.test_generator.controllers;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ""})
public class CaseControllerTests {
    static private CaseController caseController;
    static private SuitService suitService;
//
//    @BeforeClass
//    public static void setup() {
//        caseController = new CaseController();
//        suitService = new SuitService();
//        suitService.addSuit(new Suit(1, "name1", "first suit description"));
//        suitService.addSuit(new Suit(2, "name2", "second suit description"));
//    }
//
//    @Test
//    public void test1() {
//        Case case1 = new Case((long) 1, "", "", suitService.getSuit((long) 1));
//        Case case2 = new Case((long) 2, "", "", suitService.getSuit((long) 1));
//        Case case3 = new Case((long) 3, "", "", suitService.getSuit((long) 1));
//        caseController.addCaseToSuit(1, case1);
//        caseController.addCaseToSuit(2, case2);
//        caseController.addCaseToSuit(3, case3);
//    }
//
//    @Test
//    public void test2() {
//        Case case1 = new Case((long) 1, "new description", "", suitService.getSuit((long) 1));
//        //caseController.updateCase(case1);
//    }

//    @Test
//    public void test4() {
//
//    }
//
//    @Test
//    public void test5() {
//
//    }
}
