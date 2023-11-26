// package apapedia.user.restservice;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import apapedia.user.model.User;
// import apapedia.user.repository.UserDb;

// public class UserDetailsServiceImpl implements UserDetailsService {

//     @Autowired
//     private UserDb userDb;

//     @Autowired
//     private PasswordEncoder encoder;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Optional<User> userDetail = userDb.findByUsername(username);

//         return userDetail.map(UserInfoDetails::new)
//                 .orElseThrow(() -> new UsernameNotFoundException("Username tidak ditemukan " + username));
//     }
    
// }
