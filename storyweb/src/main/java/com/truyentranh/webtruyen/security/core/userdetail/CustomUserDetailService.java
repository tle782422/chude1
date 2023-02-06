package com.truyentranh.webtruyen.security.core.userdetail;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.security.bruteforce.BruteForceProtectionService;
import com.truyentranh.webtruyen.security.models.Group;
import com.truyentranh.webtruyen.security.models.User;
import com.truyentranh.webtruyen.security.repositories.UserRepository;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
    private HttpServletRequest request;
	
    @Resource(name="bruteForceProtectionService")
    private BruteForceProtectionService bruteForceProtectionService;
	
    @Resource
    UserRepository userRepository;

    @Override
    public CustomUser loadUserByUsername(String email) throws UsernameNotFoundException {
    	String ip = getClientIP();
        if (bruteForceProtectionService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
    	
        final User customer = userRepository.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }
        boolean enabled = !customer.isAccountVerified(); // we can use this in case we want to activate account after customer verified the account
        CustomUser user = CustomUser.CustomUserBuilder.aCustomUser().
                 withUsername(customer.getEmail())
                .withPassword(customer.getPassword())
                .withEnabled(customer.isLoginDisabled())
                .withAuthorities(getAuthorities(customer))
                .withSecret(customer.getSecret())
                .withAccountNonLocked(false)
                .build();

        return user;
    }



    private Collection<GrantedAuthority> getAuthorities(User user){
        Set<Group> userGroups = user.getUserGroups();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userGroups.size());
        for(Group userGroup : userGroups){
            authorities.add(new SimpleGrantedAuthority(userGroup.getCode().toUpperCase()));
        }

        return authorities;
    }
    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}