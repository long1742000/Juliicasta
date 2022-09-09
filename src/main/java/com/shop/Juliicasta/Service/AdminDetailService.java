package com.shop.Juliicasta.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;

@Service
public class AdminDetailService implements UserDetailsService {

	@Autowired
	AccountMapper accountMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountExample accountExample = new AccountExample();
		accountExample.createCriteria().andUsernameEqualTo(username);

		List<Account> list = accountMapper.selectByExample(accountExample);

		if (list.size() > 0) {
			Account acc = list.get(0);

			if (acc.getRole().equals("ROLE_ADMIN") && acc.getEnable()) {
				List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

				GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
				grantList.add(authority);

				UserDetails admin = new User(username, acc.getPassword(), grantList);

				return admin;
			} else if(acc.getRole().equals("ROLE_EMPLOYEE") && acc.getEnable()) {
				List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

				GrantedAuthority authority = new SimpleGrantedAuthority("EMPLOYEE");
				grantList.add(authority);

				UserDetails employee = new User(username, acc.getPassword(), grantList);

				return employee;
			}else {
				new UsernameNotFoundException("Login fail !");
			}
		} else {
			new UsernameNotFoundException("Login fail !");
		}

		return null;
	}
}
