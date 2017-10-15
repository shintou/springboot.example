package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.entity.User;

public interface FooService {

	public void setUserMapper(UserMapper userMapper);

	public User doSomeBusinessStuff(String userId);

}