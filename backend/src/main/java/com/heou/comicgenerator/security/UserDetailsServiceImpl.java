package com.heou.comicgenerator.security;

import com.heou.comicgenerator.entity.SysUser;
import com.heou.comicgenerator.mapper.SysMenuMapper;
import com.heou.comicgenerator.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userMapper.selectUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (user.getStatus() != 1) {
            throw new UsernameNotFoundException("用户已被禁用");
        }
        List<String> perms = menuMapper.selectPermsByUserId(user.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String perm : perms) {
            if (perm != null && !perm.isEmpty()) {
                authorities.add(new SimpleGrantedAuthority(perm));
            }
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}