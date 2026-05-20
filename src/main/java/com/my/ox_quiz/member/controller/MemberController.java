package com.my.ox_quiz.member.controller;

import com.my.ox_quiz.member.dto.MemberDto;
import com.my.ox_quiz.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping({"","/","/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("dto", new MemberDto());
        return "join";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @GetMapping("/my-page")
    public String myPage() {
        return "my-page";
    }

    @GetMapping("/member-list")
    public String memberList() {
        return "member-list";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("dto") MemberDto dto) {
        memberService.join(dto);
        return "redirect:/member/login";
    }

    @PostMapping("/login")
    public String login(MemberDto dto, HttpSession session) {
        MemberDto loginDto = memberService.login(dto);
        if (loginDto != null) {
            session.setAttribute("member", loginDto);
            return "redirect:/member/index";
        } else {
            return "redirect:/member/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/index";
    }
}
