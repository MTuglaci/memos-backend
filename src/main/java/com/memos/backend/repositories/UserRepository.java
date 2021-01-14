package com.memos.backend.repositories;

import com.memos.backend.models.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTable, String> {

    UserTable findByUsername(String user);
    UserTable findByEmail(String email);
}
