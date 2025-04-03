package dev.Zerphyis.library.Controller;

import dev.Zerphyis.library.Entity.Datas.DataUsers;
import dev.Zerphyis.library.Entity.User.Users;
import dev.Zerphyis.library.Service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    @Test
    void testRegisterUser() throws Exception {
        DataUsers dataUsers = new DataUsers("João", "joao@email.com", "123456789");
        Users user = new Users(dataUsers);
        when(usersService.registerUser(any(DataUsers.class))).thenReturn(user);

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"João\",\"email\":\"joao@email.com\",\"phone\":\"123456789\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João"))
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void testGetAllUsers() throws Exception {
        Users user1 = new Users(new DataUsers("João", "joao@email.com", "123456789"));
        Users user2 = new Users(new DataUsers("Maria", "maria@email.com", "987654321"));
        List<Users> usersList = Arrays.asList(user1, user2);

        when(usersService.getAllUsers()).thenReturn(usersList);

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("João"))
                .andExpect(jsonPath("$[1].name").value("Maria"));
    }

    @Test
    void testGetUserById() throws Exception {
        Users user = new Users(new DataUsers("João", "joao@email.com", "123456789"));
        when(usersService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João"));
    }

    @Test
    void testUpdateUser() throws Exception {
        DataUsers dataUsers = new DataUsers("João Atualizado", "joao@email.com", "123456789");
        Users updatedUser = new Users(dataUsers);

        when(usersService.updateUsers(eq(1L), any(DataUsers.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"João Atualizado\",\"email\":\"joao@email.com\",\"phone\":\"123456789\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João Atualizado"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(usersService).deleteUser(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }

}