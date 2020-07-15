package com.hai.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hai.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(value = "select * from blog.user_blog where username=:username and password=:password", nativeQuery = true)
	public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}