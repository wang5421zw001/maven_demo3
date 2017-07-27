package com.hmz.repository;

import com.hmz.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


/**
 * Created by 王瑆 on 2017/7/10.
 */
public interface UserRepository extends JpaRepository<User,String>{
   User findByUserName(String userName);
   User findByUserId(int userId);
   @Query("select u from User u where u.userId in (ids)")
   List<User> findAll(String ids);
}
