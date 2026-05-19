package com.my.ox_quiz.member.service;

import com.my.ox_quiz.member.dto.MemberDto;
import com.my.ox_quiz.member.entity.Member;
import com.my.ox_quiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void join(MemberDto dto){
        Member user = new Member();
        user.setId(dto.getId());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAnswerTrue(dto.getAnswerTrue());
        user.setAnswerFalse(dto.getAnswerFalse());
        memberRepository.save(user);
    }

    public List<MemberDto> findAll(){
        return memberRepository.findAll()
                .stream()
                .map(x->MemberDto.toDto(x))
                .toList();
    }

    public MemberDto findById(String id){
        Member user = memberRepository.findById(id).orElse(null);
        if(user != null){
            return MemberDto.toDto(user);
        }
        return null;
    }

    public MemberDto login(MemberDto dto){
        MemberDto loginDto = findById(dto.getId());
        if(loginDto == null) return null;
        boolean matches = passwordEncoder.matches(
                dto.getPassword(),
                loginDto.getPassword()
        );
        if(matches){
            return loginDto;
        } else {
            return null;
        }
    }
}
