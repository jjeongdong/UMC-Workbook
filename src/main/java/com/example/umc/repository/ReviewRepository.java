package com.example.umc.repository;

import com.example.umc.domain.Member;
import com.example.umc.domain.Review;
import com.example.umc.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    @Query("SELECT r FROM Review r JOIN FETCH r.member m WHERE m = :member")
    Page<Review> findAllByMember(@Param("member") Member member, PageRequest pageRequest);
}
