package com.yonsai.books.service;

import com.yonsai.books.dto.UserRegisterRequest;
import com.yonsai.books.repository.UserMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserMemoryRepository userRepository;


  public void createOrGetUser (String sessionId , String name) {

    Optional<String> savedName = userRepository.findBySessionId(sessionId);

    if (savedName.isPresent()) { // 값이 있는지 ?
      if (savedName.get().equals(name)) {
        return; // 같은 유저 → 정상
      }
      throw new IllegalArgumentException("이미 다른 이름으로 로그인된 세션입니다.");
    }

    userRepository.save(sessionId , name);
  }

  public String findByUser(String sessionId) {
    return userRepository.findBySessionId(sessionId)
            .orElseThrow(() -> new IllegalArgumentException("해당 SESSION ID로 가입된 유저가 없습니다."));
  }
}
