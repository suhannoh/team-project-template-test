package com.yonsai.books.repository;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserMemoryRepository {
  Map<String , String> memoryUserList = new HashMap<>();

  // 저장
  public void save(String sessionId , String name ) {
    memoryUserList.put(sessionId, name);
  }

  // 세션 ID로 저장된 name 조회
  public Optional<String> findBySessionId (String sessionId) {
    String name = memoryUserList.get(sessionId);
    return Optional.ofNullable(name);
  }
 }
