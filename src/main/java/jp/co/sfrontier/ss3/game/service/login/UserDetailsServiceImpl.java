package jp.co.sfrontier.ss3.game.service.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.entity.UserInformationTbl;
import jp.co.sfrontier.ss3.game.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserMapper userMapper;

	public UserDetailsServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserInformationTbl user = userMapper.findByUserNameOrMailAddress(username);

		if (user == null) {
			throw new UsernameNotFoundException("ユーザが見つかりません");
		}

		// user(UserInformationTbl)を、Spring Security用の形(UserDetails)に変換して返す
		return new LoginUserDetails(user);
	}
}
