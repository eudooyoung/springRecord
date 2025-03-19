package com.multi.shop.member.service;

import com.multi.shop.member.model.dao.MemberMapper;
import com.multi.shop.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 서비스빈 등록
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, BCryptPasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public int insertMember(MemberDTO dto) {
        String encodePassword = passwordEncoder.encode(dto.getPw());
        dto.setPw(encodePassword);

        int result = memberMapper.insertMember(dto);
//        int result1 = memberMapper.insertMember(dto); //트랜젝션 확인용, 트렌젝셔널 어노테이션이 없으면 하나만 들어감

        System.out.println(result);
        if (result < 0) {
            throw new RuntimeException("insertMember fail: count =" + result);
        }
        return result;
    }

    @Override
    public MemberDTO loginCheck(MemberDTO dto) {

        MemberDTO loginMember = null;

        /* 로그인 요청한 원문 비밀번호화 저장되어있는 암호화된 비밀번호가 일치하는지 확인한다.
         * //matches(평문, 암호화) -> true, false 반환
         * */

        String encPwd = memberMapper.selectEncryptedPwd(dto);

        System.out.println(encPwd); // 암호화
        System.out.println(dto.getPw()); // 평문


        if (passwordEncoder.matches(dto.getPw(), encPwd)) {
            loginMember = new MemberDTO();
            loginMember = memberMapper.selectLoginMember(dto);
            System.out.println(loginMember);
        }

        return loginMember;
    }


}
