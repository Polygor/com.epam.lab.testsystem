package com.epam.testsystem.rest;

import com.epam.testsystem.dto.UserTestDto;
import com.epam.testsystem.dto.UserTestsDto;
import com.epam.testsystem.model.Test;
import com.epam.testsystem.model.TestCase;
import com.epam.testsystem.model.User;
import com.epam.testsystem.service.TestCaseService;
import com.epam.testsystem.service.TestService;
import com.epam.testsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/tests-users/")
public class UserTestController {

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    UserService userService;

    @Autowired
    TestService testService;

    @RequestMapping(value = "/.rest", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody List<UserTestDto> getTestsUsers() {
//        @PathVariable String name, ModelMap model

        List<TestCase> testCases = testCaseService.findAll();
        List<UserTestDto> users_tests = new ArrayList<>();

        
        for (TestCase testCase : testCases) {
            users_tests.add(new UserTestDto(testCase.getUser().getId(), testCase.getTest().getId()));
        }

        return users_tests;
    }

    @RequestMapping(value = "/sendTest/.rest", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    ResponseEntity<UserTestsDto> sendTestData() {

        UserTestsDto userTestsDto = new UserTestsDto();
        userTestsDto.setUserId("45");
        List<String> testsIds = new ArrayList<>();
        testsIds.add("34");
        testsIds.add("42");
        testsIds.add("56");
        userTestsDto.setTestIds(testsIds);

        return new ResponseEntity<UserTestsDto>(userTestsDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/setUserTests/.rest", method = RequestMethod.POST)
    public void setUserTests(@RequestBody UserTestsDto userTestsDto) {

        User user = userService.findById((long) Integer.parseInt(userTestsDto.getUserId()));
        updateUserTestCases(user, userTestsDto.getTestIds());
        userService.save(user);
    }

    private void updateUserTestCases(User user, List<String> testIds) {
        for (String testIdString : testIds) {
            Long testId = Long.valueOf(testIdString);
            if(!user.containsTestCase(testId)) {
                user.addTestCase(new TestCase(user, testService.findById(testId)));
            }
        }
        List<TestCase> testCases = user.getTestCases();
        List<TestCase> testCasesForDeleting = new ArrayList<>();
        for (TestCase testCase : testCases) {
            if(!testIds.contains(testCase.getTest().getId().toString())) {
                testCasesForDeleting.add(testCase);
            }
        }
        for (TestCase testCase : testCasesForDeleting) {
            testCases.remove(testCase);
        }
    }
}
