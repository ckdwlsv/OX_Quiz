package com.my.ox_quiz.member.service;

import com.my.ox_quiz.member.dto.MemberDto;
import com.my.ox_quiz.member.entity.Member;
import com.my.ox_quiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void join(MemberDto dto){
        Optional<Member> existing = memberRepository.findById(dto.getId());
        if(existing.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        Member member = new Member();
        member.setId(dto.getId());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setAnswerTrue(dto.getAnswerTrue());
        member.setAnswerFalse(dto.getAnswerFalse());
        memberRepository.save(member);
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
