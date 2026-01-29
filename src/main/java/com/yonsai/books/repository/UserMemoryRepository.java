package com.yonsai.books.repository;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserMemoryRepository {
  Map<String , String> memoryUserList = new HashMap<>();


  public void save(String sessionId , String name ) {
    memoryUserList.put(sessionId, name);
  }

  public boolean existsBySessionId (String sessionId) {
    String name = memoryUserList.get(sessionId);
    return name != null; // 이름이 있는 경우 참 반환
  }
  public Optional<String> findBySessionId (String sessionId) {
    String name = memoryUserList.get(sessionId);
    return Optional.ofNullable(name);
  }
  public String getNameBySessionId (String sessionId) {
    return memoryUserList.get(sessionId);
  }
 }
