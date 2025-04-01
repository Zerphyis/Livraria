package dev.Zerphyis.library.Controller;

import dev.Zerphyis.library.Entity.Datas.DataUsers;
import dev.Zerphyis.library.Entity.User.Users;
import dev.Zerphyis.library.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsersController {
    @Autowired
    UsersService service;

    @PostMapping()
    public ResponseEntity<Users> register(@RequestBody DataUsers data){
      Users users=service.registerUser(data);
        return ResponseEntity.ok(users);
    }
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PutMapping("/{id}")
   public  ResponseEntity<Users> update(@PathVariable("id")Long id, @RequestBody DataUsers data){
        return ResponseEntity.ok(service.updateUsers(id, data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
