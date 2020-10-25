/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import school.model.auth.Users;

/**
 *
 * @author Md Belayet Hossin
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Users findByEmail(String email);
    
}
