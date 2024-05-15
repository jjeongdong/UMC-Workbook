package com.example.umc.service.MemberService;

import com.example.umc.domain.Member;
import com.example.umc.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);
}
