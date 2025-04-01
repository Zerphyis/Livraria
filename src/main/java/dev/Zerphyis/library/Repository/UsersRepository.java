package dev.Zerphyis.library.Repository;

import dev.Zerphyis.library.Entity.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
}
