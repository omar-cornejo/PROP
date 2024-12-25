package test;

import main.domain.classes.User;
import main.domain.exceptions.InvalidPasswordException;
import main.domain.exceptions.InvalidUsernameException;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUser {

    @Test
    public void testConstructor() {
        User user = new User("John Doe", "johnd", "password");

        assertEquals("John Doe", user.getName());
        assertEquals("johnd", user.getUsername());
        assertEquals("password", user.getPassword());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidName() {
        new User("", "johnd", "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidUsername() {
        new User("John Doe", "", "password");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidPassword() {
        new User("John Doe", "johnd", "");
    }

    @Test
    public void testAuthenticate() {
        User user = new User("John Doe", "johnd", "password");

        assertTrue(user.authenticate("johnd", "password"));
        assertFalse(user.authenticate("wronguser", "password"));
        assertFalse(user.authenticate("johnd", "wrongpassword"));

        try {
            user.authenticate(null, "password");
            fail("Se esperaba IllegalArgumentException para un nombre de usuario nulo.");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre de usuario y la contraseña no pueden ser nulos.", e.getMessage());
        }
    }

    @Test
    public void testUpdateUsername() {
        User user = new User("John Doe", "johnd", "password");

        user.setUsername("john_doe");
        assertEquals("john_doe", user.getUsername());

        try {
            user.setUsername("");
            fail("Se esperaba InvalidUsernameException.");
        } catch (InvalidUsernameException e) {
            assertEquals("El nuevo nombre de usuario no puede estar vacío.", e.getMessage());
        }
    }

    @Test
    public void testUpdatePassword() {
        User user = new User("John Doe", "johnd", "password");

        try {
            user.setPassword("password", "newpassword");
            assertEquals("newpassword", user.getPassword());
        } catch (InvalidPasswordException e) {
            fail("No se esperaba InvalidPasswordException: " + e.getMessage());
        }

        try {
            user.setPassword("wrongpassword", "anotherpassword");
            fail("Se esperaba InvalidPasswordException.");
        } catch (InvalidPasswordException e) {
            assertEquals("La contraseña actual es incorrecta.", e.getMessage());
        }

        try {
            user.setPassword("newpassword", "");
            fail("Se esperaba IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertEquals("La nueva contraseña no puede estar vacía.", e.getMessage());
        }
    }
}
