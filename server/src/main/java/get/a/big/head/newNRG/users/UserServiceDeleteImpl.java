package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class UserServiceDeleteImpl implements UserServiceDelete {

    private final UserRepository userRepository;

    @Override
    public void deleteUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Указанный userId не существует"));
        userRepository.delete(user);
    }
}
