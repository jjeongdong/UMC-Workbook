package com.example.umc.service.MemberService;


import com.example.umc.domain.Member;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

}
