package dev.Zerphyis.library.Service;


import dev.Zerphyis.library.Entity.Datas.DataUsers;
import dev.Zerphyis.library.Entity.User.Users;
import dev.Zerphyis.library.Exeception.UserNotFoundExeception;
import dev.Zerphyis.library.Repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {
    @Mock
    private UsersRepository repository;

    @InjectMocks
    private UsersService service;

    private Users user;
    private DataUsers data;

    @BeforeEach
    void setUp() {
        data = new DataUsers("Otávio", "otavio@email.com", "123456789");
        user = new Users(data);
        user.setId(1L);
    }

    @Test
    void shouldRegisterUser() {
        when(repository.save(any(Users.class))).thenReturn(user);

        Users savedUser = service.registerUser(data);

        assertNotNull(savedUser);
        assertEquals(user.getName(), savedUser.getName());
        verify(repository, times(1)).save(any(Users.class));
    }

    @Test
    void shouldReturnAllUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<Users> users = service.getAllUsers();

        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnUserById() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        Users foundUser = service.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundExeception.class, () -> service.getUserById(1L));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldDeleteUser() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.deleteUser(1L));

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingUser() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(UserNotFoundExeception.class, () -> service.deleteUser(1L));

        verify(repository, never()).deleteById(1L);
    }

    @Test
    void shouldUpdateUser() {
        Long userId = 1L;
        DataUsers data = new DataUsers("Novo Nome", "novo@email.com", "123456789");


        DataUsers existingData = new DataUsers("Nome Antigo", "antigo@email.com", "987654321");
        Users existingUser = new Users(existingData);
        existingUser.setId(userId);

        when(repository.existsById(userId)).thenReturn(true);
        when(repository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(repository.save(any(Users.class))).thenReturn(existingUser);

        Users updatedUser = service.updateUsers(userId, data);

        assertNotNull(updatedUser);
        assertEquals(data.name(), updatedUser.getName());
        assertEquals(data.email(), updatedUser.getEmail());
        assertEquals(data.phone(), updatedUser.getPhone());

        // Verificações
        verify(repository, times(1)).existsById(userId);
        verify(repository, times(1)).findById(userId);
        verify(repository, times(1)).save(any(Users.class));
    }
}