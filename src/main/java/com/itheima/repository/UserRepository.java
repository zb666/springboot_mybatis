package com.itheima.repository;

import com.itheima.domain.User;
import com.itheima.domain.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserBean, Long> {
    List<UserBean> findAll();
}
