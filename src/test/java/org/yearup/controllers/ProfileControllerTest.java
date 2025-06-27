package org.yearup.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProfileControllerTest {
    private ProfileDao profileDao;
    private UserDao userDao;
    private ProfileController controller;
    private Principal principal;

    @BeforeEach
    public void setup() {
        profileDao = mock(ProfileDao.class);
        userDao = mock(UserDao.class);
        principal = mock(Principal.class);
        controller = new ProfileController(profileDao, userDao);
    }

    @Test
    public void testGetProfile() {
        // Arrange
        when(principal.getName()).thenReturn("testUser");

        User user = new User();
        user.setId(1);
        user.setUsername("testUser");

        when(userDao.getByUserName("testUser")).thenReturn(user);

        Profile profile = new Profile(
                1, "John", "Kassaye", "123-456", "john1567@gmail.com",
                "123 John St", "Seatac", "WA", "98188"
        );
        when(profileDao.getProfile(1)).thenReturn(profile);

        // Act
        Profile result = controller.getProfile(principal);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Kassaye", result.getLastName());
    }

    @Test
    public void testUpdateProfile() {
        // Arrange
        when(principal.getName()).thenReturn("testUser");

        User user = new User();
        user.setId(1);
        user.setUsername("testUser");
        when(userDao.getByUserName("testUser")).thenReturn(user);

        Profile profile = new Profile(
                1, "John", "Kassaye", "123-456", "john1567@gmail.com",
                "123 John St", "Seatac", "WA", "98188"
        );
        when(profileDao.updateProfile(1, profile)).thenReturn(profile);

        // Act
        Profile result = controller.updateProfile(principal, profile);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Seatac", result.getCity());
    }
}