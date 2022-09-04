package com.cStates.member.service;

import com.cStates.exception.BusinessLogicException;
import com.cStates.exception.ExceptionCode;
import com.cStates.member.entity.Member;
import com.cStates.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * V2
 *  - 메서드 구현
 *  - DI 적용
 */
@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 1. MemberRepository DI => @AllArgsConstructor

    public Member createMember(Member member) {
        // 2. 이미 등록된 이메일인지 검증
        verifyExistsEmail(member.getEmail());

        // 3. 회원 정보 저장
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        // 4. 존재하는 회원인지 검증
        Member findMember = findVerifiedMember(member.getMemberId());

        // 5. 업데이트 - 이름 / 휴대폰 정보
        Optional.ofNullable(member.getName()).ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone()).ifPresent(phone -> findMember.setPhone(phone));

        // 6. 회원 정보 업데이트
        return memberRepository.save(findMember);
    }

    // 7. 특정 회원 정보 조회
    public Member findMember(long memberId) {

        return findVerifiedMember(memberId);
    }

    // 8. 모든 회원 정보 조회
    public List<Member> findMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    // 9. 특정 회원 정보 삭제
    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    // 10. 이미 존재하는 회원인지 검증
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    // 11. 이미 등록된 이메일 주소인지 검증
    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
