package com.cStates.member;

import com.cStates.dto.MemberPatchDto;
import com.cStates.dto.MemberPostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberPostDto memberPostDto) {

        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    // 한 명의 회원 정보 조회
    @GetMapping("/{memberId}")
    public ResponseEntity getMember(@PathVariable("memberId")long memberId) {
        System.out.println("# memberId: " + memberId);

        // not implementation

        return new ResponseEntity<Map>(HttpStatus.OK);
    }

    // 모든 회원 정보 조회
    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 정보 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") long memberId,
                                      @RequestBody MemberPatchDto memberPatchDto) {

        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setName("홍길동");

        // No need Business Logic

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") long memberId) {

        // No nee Business Logic

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}