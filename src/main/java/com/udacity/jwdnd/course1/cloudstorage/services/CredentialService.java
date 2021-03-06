package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredential(int userId){
        return credentialMapper.getCredential(userId);
    }

    public int addCredential(Credential credential, int userId){
        credential.setUserId(userId);
        return credentialMapper.addCredential(credential);
    }

    public int updateCredential(Credential credential){
        return credentialMapper.updateCredential(credential);
    }

    public int deleteCredential(int credentialId){
        return credentialMapper.delete(credentialId);
    }
}
