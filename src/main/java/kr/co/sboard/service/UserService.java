package kr.co.sboard.service;

import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.entity.TermsEntity;
import kr.co.sboard.entity.UserEntity;
import kr.co.sboard.repository.TermsRepository;
import kr.co.sboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private TermsRepository termsRepository;

    @Autowired
    private UserRepository userRepository;

    public TermsEntity findByTerm(){
    return termsRepository.findById(1).get();

    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void  save(UserDTO dto){
            //비밀번호 암호화
           dto.setPass1(passwordEncoder.encode(dto.getPass1()));

        //repository를 사용하려면 dto를 entity로 변경
        UserEntity entity = dto.toEntity();

        //DB Insert
        userRepository.save(entity);
    }


    public int countUid(String uid){
        return userRepository.countByUid(uid);
    }

    public int countNick(String nick){
        return userRepository.countByNick(nick);
    }

}
