package es.capgemini.cca.canbemini.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.security.UserDetailsImpl;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<Users> findAll() {
        // TODO Auto-generated method stub
        return (List<Users>) this.usersRepository.findAll();
    }

    @Override
    public Users findUsers(Long id) {
        return this.usersRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUsers(Long id) {
        this.usersRepository.deleteById(id);

    }

    @Override
    public Users findUsersByEmail(String email) {
        return this.usersRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void saveUsers(Long id, UsersDto usersDto) {
        Users users = null;

        if (id == null)
            users = new Users();
        else
            users = this.findUsers(id);

        users.setEmail(usersDto.getEmail());

        this.usersRepository.save(users);
    }

    public Boolean verifyUser(Long userId, UserDetailsImpl userDetailsImpl) {
        Boolean ok = false;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users user = this.usersRepository.findByEmail(authentication.getPrincipal().toString()).orElse(null);
        Long loggedUser = user.getId();

        if (userId.equals(loggedUser)) {
            ok = true;
        }
        return ok;

    }

}
