package com.my.ox_quiz.config;

import com.my.ox_quiz.member.entity.Member;
import com.my.ox_quiz.member.entity.MemberStatus;
import com.my.ox_quiz.member.entity.RoleType;
import com.my.ox_quiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminAccountInitializer implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        boolean exists = memberRepository.existsByIdEquals("root");

        if (!exists){
            Member admin = Member.builder()
                    .id("root")
                    .password(passwordEncoder.encode("admin"))
                    .role(RoleType.ADMIN)
                    .status(MemberStatus.APPROVED)
                    .answerTrue(0)
                    .answerFalse(0)
                    .build();

            memberRepository.save(admin);

            System.out.println("관리자 계정 자동 생성 완료");
        }
    }
}
