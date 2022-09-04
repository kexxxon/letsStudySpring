package com.cStates.member.mapper;

import com.cStates.member.dto.MemberPatchDto;
import com.cStates.member.dto.MemberPostDto;
import com.cStates.member.dto.MemberResponseDto;
import com.cStates.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
