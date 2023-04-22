package get.newNRG.users.security;

import get.newNRG.exception.NotFoundException;
import get.newNRG.users.UserMapper;
import get.newNRG.users.UserRepository;
import get.newNRG.users.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("Пользователь не зарегистрирован"));
        return UserMapper.fromUser(user);
    }
}
