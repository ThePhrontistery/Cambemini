package es.capgemini.cca.canbemini.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // TODO Auto-generated method stub
        return this.usersRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUsers(Long id) {
        this.usersRepository.deleteById(id);

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

}
