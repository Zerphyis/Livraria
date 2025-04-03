package dev.Zerphyis.library.Service;

import dev.Zerphyis.library.Entity.Datas.DataUsers;
import dev.Zerphyis.library.Entity.User.Users;
import dev.Zerphyis.library.Exeception.UserNotFoundExeception;
import dev.Zerphyis.library.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repository;

    public Users registerUser(DataUsers data) {
        var newUser = new Users(data);
        return repository.save(newUser);
    }

    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    public Users getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundExeception("Usuário com ID " + id + " não encontrado"));
    }

    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundExeception("Usuário com ID " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

    public Users updateUsers(Long id, DataUsers data) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundExeception("Usuário com ID " + id + " não encontrado");
        }

        Users user = getUserById(id);
        user.setName(data.name());
        user.setEmail(data.email());
        user.setPhone(data.phone());
        return repository.save(user);
    }
}
