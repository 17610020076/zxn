package com.zxn.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;

public class ShiroRealm extends AuthenticatingRealm{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		
		UsernamePasswordToken userToken =	(UsernamePasswordToken)token;
		
		String username=userToken.getUsername();
		
		
		if("zhao".equals(username)){
			throw new UnknownAccountException("用户不存在");
		}
		
		if("xi".equals(username)){
			throw new LockedAccountException("用户被锁定");
		}
		System.out.println("realm   "+token.hashCode());
		
		Object principal=username;
		Object credentials="fc1709d0a95a6be30bc5926fdb7f22f4";
		String realmName=getName();
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		return info;
	}

	public static void main(String[] args) {
		String algorithmName="MD5";
		Object credentials="123456";
		Object salt=null;
		int hashIterations=1024;
		
		SimpleHash simpleHash = new SimpleHash(algorithmName, credentials, salt, hashIterations);
		
		System.out.println(simpleHash);
	}
}
