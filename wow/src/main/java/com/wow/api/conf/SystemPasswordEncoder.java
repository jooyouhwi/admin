package com.wow.api.conf;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.wow.api.dao.MaccoMapper;
import com.wow.api.dao.SystemMapper;




@Component
public class SystemPasswordEncoder implements PasswordEncoder{
	/*
	 * @Autowired private SmWownetPgService smService;
	 */

	@Autowired
	private SystemMapper systemMapper;
	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		/*
		String[] passwdAndComId = StringUtils.split(encodedPassword, String.valueOf(Character.LINE_SEPARATOR));
        if (passwdAndComId == null || passwdAndComId.length < 2) {
        	return false;
        }
        String passwd =  encodedPassword.substring(passwdAndComId[0].length() + String.valueOf(Character.LINE_SEPARATOR).length());
        */
        String pawd = systemMapper.system_encrypt(rawPassword.toString());
        
		//return this.smService.encryptPass(passwdAndComId[0], rawPassword.toString()).equals(passwd);
        //equals(passwd); 체크해야됨
        System.out.println("pawd   : "+ pawd);
        System.out.println("equals : "+pawd.equals(encodedPassword));
        
        //return systemMapper.System_encrypt(rawPassword.toString()).equals(passwd);
        //return pawd.equals(passwd);
        return pawd.equals(encodedPassword);
	}

	
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return null;
	}
}
