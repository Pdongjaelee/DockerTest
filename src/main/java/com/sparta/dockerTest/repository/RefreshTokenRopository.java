package com.sparta.dockerTest.repository;

import com.sparta.dockerTest.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRopository extends JpaRepository<RefreshToken, Long> {

}
