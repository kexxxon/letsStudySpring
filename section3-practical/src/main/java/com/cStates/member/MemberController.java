package com.cStates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members") // (1) produces 설정 제거
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {

        // (2) request json -> Map object
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        // (3) 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id")long memberId) {
        System.out.println("# memberId: " + memberId);

        // not implementation

        // (4) 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");

        // not implementation

        // (5) 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(HttpStatus.OK);
    }
}