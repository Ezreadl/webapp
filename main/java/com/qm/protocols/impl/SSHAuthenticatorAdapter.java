package com.qm.protocols.impl;

import java.io.File;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;

import com.mindbright.ssh.SSH;
import com.mindbright.ssh.SSHAuthenticator;
import com.mindbright.ssh.SSHClientUser;
import com.mindbright.ssh.SSHRSAKeyFile;

public class SSHAuthenticatorAdapter implements SSHAuthenticator{
	private String userName;
	private String password;
	public SSHAuthenticatorAdapter(String userName,String password){
		this.userName=userName;
		this.password=password;
	}
	public String getUsername(SSHClientUser origin) throws IOException {
        return userName;
    }
    public String getPassword(SSHClientUser origin) throws IOException {
        return password;
    }
    public String getChallengeResponse(SSHClientUser origin, String challenge) throws IOException {
        return null;
    }
    public int[] getAuthTypes(SSHClientUser origin) {
        return SSH.getAuthTypes("password");
    }
    public int getCipher(SSHClientUser origin) {
        return SSH.CIPHER_3DES;
    }
    public SSHRSAKeyFile getIdentityFile(SSHClientUser origin) throws IOException {
        String idfile = System.getProperty("user.home") + File.separatorChar + ".ssh" + 
            File.separatorChar + "identity";
        return new SSHRSAKeyFile(idfile);
    }

    public String getIdentityPassword(SSHClientUser origin)  throws IOException {
        return null;
    }
    public boolean verifyKnownHosts(RSAPublicKey hostPub) throws IOException {
        return true;
    }
}
