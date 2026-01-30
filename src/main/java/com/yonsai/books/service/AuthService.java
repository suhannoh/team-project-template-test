package com.yonsai.books.service;

import com.yonsai.books.dto.UserRegisterRequest;
import com.yonsai.books.repository.UserMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *  AuthController 의 기능을 처리하는 Service
 *  권한 인증을 맡음
 */
@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserMemoryRepository userRepository;

  /**
   * 로그인 기능 구현
   *  - 회원가입 없이 이름만으로 로그인 처리
   *  - 세션 ID를 기준으로 메모리 저장소에 유저를 관리한다
   *
   *  - 하나의 세션에 하나의 이름만 허용
   *    - 문제점 : 로그아웃 → session.invalidate()
   *              새로운 sessionId를 생성해서 새 가입이 허용됨 .
   *              로그인 흉내만 내는 걸로 테스트
   * @param sessionId
   * @param name
   */
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
}
