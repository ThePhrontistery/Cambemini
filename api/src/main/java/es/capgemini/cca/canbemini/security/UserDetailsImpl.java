package es.capgemini.cca.canbemini.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.capgemini.cca.canbemini.userKanbanPermission.UserKanbanPermission;
import es.capgemini.cca.canbemini.users.Users;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Users user;

    // private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Users user) {
        this.user = user;
    }

    public static UserDetailsImpl build(Users user) {

        return new UserDetailsImpl(user);
    }

    public Long getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) o;
        return Objects.equals(user.getId(), userDetailsImpl.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserKanbanPermission ukp : user.getUser_kanban_permission()) {
            authorities.add(
                    new SimpleGrantedAuthority(ukp.getPermission().getRol().toUpperCase() + ukp.getKanban().getId()));

        }
        return authorities;
    }
}