package com.epam.mock.web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.mock.service.UserService;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.epam.mock.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDemoTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController systemUnderTesting;

    @Test
    public void testGetListOfProviders() throws Exception {
        // prepare
        when(userService.getUsers()).thenReturn(ImmutableList.of());
        // testing
        List<User> users = systemUnderTesting.getUsers();
        // validate
        verify(userService).getUsers(); // checking was method called
    }
}