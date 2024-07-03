package dodo.reddit.models.User;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;


import java.util.HashSet;

@Getter
public enum UserRoleEnum {
    USER,
    ADMIN;

    public Set<GrantedAuthority> getAuth(){
        Set<GrantedAuthority> auth = new HashSet<>();

        auth.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return auth;
    }
}
