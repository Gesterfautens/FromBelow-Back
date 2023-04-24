package com.frombelow.web.app.service;

import com.frombelow.web.app.entity.Partida;
import com.frombelow.web.app.entity.User;
import com.frombelow.web.app.repository.PartidaRepository;
import com.frombelow.web.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartidaRepository partidaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);
        if(optUser.isEmpty()){
            throw new UsernameNotFoundException("No se encuentra el usuario.");
        }else{
            User user = optUser.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getTipo()));

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPass()
                    ,authorities);
        }
    }


    public List<String> getPartidasById(int id){
        return partidaRepository.getPartidasByPlayerId(id);
    }

    public String getUserRole(String username){
        Optional<User> optUser = userRepository.findByUsername(username);
        if(!optUser.isEmpty()){
            return optUser.get().getRole().getTipo();
        }

        return "";
    }
}
