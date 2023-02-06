package com.truyentranh.webtruyen.security.services;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.security.bruteforce.BruteForceProtectionService;
import com.truyentranh.webtruyen.security.dto.UserDto;
import com.truyentranh.webtruyen.security.dto.UserRegistrationDto;
import com.truyentranh.webtruyen.security.models.Role;
import com.truyentranh.webtruyen.security.models.User;
import com.truyentranh.webtruyen.security.repositories.UserRepository;
import com.truyentranh.webtruyen.utils.EncrytedPasswordUtils;

@Service
public class UserService implements UserServiceImpl{
	@Autowired
	private UserRepository userRepository;
	
	private String secretKey = "*()!*#A#&*VDC";
	
	@Autowired
    private HttpServletRequest request;
	
    @Resource(name="bruteForceProtectionService")
    private BruteForceProtectionService bruteForceProtectionService;
    
//    @Resource
//    private MFATokenManager mfaTokenManager;
	
      
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
	
	public List<User> findAll(){
		return userRepository.findAll();
	}	
	
	
	//Get User By Id
	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	//Delete User
	public void delete(int id) {
		userRepository.deleteById((long) id);
	}
	
	//Update User
	public void save(User user) {
		userRepository.save(user);
	}
	
	public UserService() {
		this.userRepository = userRepository;
	}
	
	public void UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				EncrytedPasswordUtils.encrytePassword(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_ADMIN")));
//			 user.setSecret(mfaTokenManager.generateSecretKey());
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	String ip = getClientIP();
        if (bruteForceProtectionService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
		User user = userRepository.findByEmail(username);
		if(user != null) {
			UserDetails userDetails = new org.springframework.security.core.userdetails.User
					(user.getEmail(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));
			return userDetails;
		} else {
			new UsernameNotFoundException("Login fail!");
		}
		return null;
		
	}

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
		
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	
//	@Override
//    public MfaTokenData mfaSetup(String email) throws UnkownIdentifierException, QrGenerationException {
//        User user= userRepository.findByEmail(email);
//        if(user == null ){
//            // we will ignore in case account is not verified or account does not exists
//            throw new UnkownIdentifierException("unable to find account or account is not active");
//        }
//       return new MfaTokenData( mfaTokenManager.getQRCode( user.getSecret()), user.getSecret());
//    }

	 private String calculateHmac(UserDetails userDto) {
	        byte[] secretKeyBytes = Objects.requireNonNull(secretKey).getBytes(StandardCharsets.UTF_8);
	        byte[] valueBytes = Objects.requireNonNull(((UserDto) userDto).getId() + "&" + ((UserDto) userDto).getLogin()).getBytes(StandardCharsets.UTF_8);

	        try {
	            Mac mac = Mac.getInstance("HmacSHA512");
	            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA512");
	            mac.init(secretKeySpec);
	            byte[] hmacBytes = mac.doFinal(valueBytes);
	            return Base64.getEncoder().encodeToString(hmacBytes);

	        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public String createToken(UserDto user) {
	        return user.getId() + "&" + user.getLogin() + "&" + calculateHmac((UserDetails) user);
	    }

	    public UserDto findByToken(String token) {
	        String[] parts = token.split("&");

	        Long userId = Long.valueOf(parts[0]);
	        String login = parts[1];
	        String hmac = parts[2];

	        UserDetails userDto = loadUserByUsername(login);

	        if (!hmac.equals(calculateHmac(userDto)) || userId != ((UserDto) userDto).getId()) {
	            throw new RuntimeException("Invalid Cookie value");
	        }

	        return (UserDto) userDto;
	    }


		public User getByEmail(String email) {		
			return userRepository.findByEmail(email);
		}
		
	    public void lock(User user) {
	        user.setAccountVerified(false);
	        user.setLockTime(new Date(LOCK_TIME_DURATION));
	         
	        userRepository.save(user);
	    }
	     
	

}