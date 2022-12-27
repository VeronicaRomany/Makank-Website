package mkanak_spring.controllers;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;

@Service
public class SecurityGuard {
    private final String secretKeyPath = "secret.key";
    private byte[] secretBytes = new byte[32];
    private JWSSigner signer;

    public SecurityGuard(){
        File secretFile = new File(secretKeyPath);
        if(!secretFile.isFile()){
            handleUnExistingKeyFiles();
        } else {
            handleExistingKeyFiles();
        }
    }


    public String getJWT(Long id){
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().claim("id",id).build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),claimsSet);
        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            System.out.println("Error signing the jwt.");
            e.printStackTrace();
        }

        return signedJWT.serialize();
    }

    public boolean verifyJWT(String s){
        SignedJWT signedJWT;
        try {
             signedJWT = SignedJWT.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        try {
            JWSVerifier verifier = new MACVerifier(secretBytes);
            return signedJWT.verify(verifier);
        } catch (JOSEException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Long extractIdFromJWT(String s) throws ParseException {
        SignedJWT signedJWT;
        signedJWT = SignedJWT.parse(s);
        return (Long) signedJWT.getJWTClaimsSet().getClaims().get("id");
    }






    private void handleExistingKeyFiles(){
        File secretFile = new File(secretKeyPath);
        try {
            this.secretBytes = Files.readAllBytes(secretFile.toPath());
            signer = new MACSigner(secretBytes);
        } catch (IOException | KeyLengthException e) {
            e.printStackTrace();
        }
    }


    private void handleUnExistingKeyFiles(){
        // recreate keys
        try {
            regenerateKeys();
        } catch (KeyLengthException e) {
            e.printStackTrace();
        }
        writeKeysToFiles();
    }


    private void regenerateKeys() throws KeyLengthException {
        SecureRandom random = new SecureRandom();
        random.nextBytes(secretBytes);
        signer = new MACSigner(secretBytes);
    }

    private void writeKeysToFiles(){
        // output public to file
        try (FileOutputStream fos = new FileOutputStream(secretKeyPath)) {
            fos.write(secretBytes);
        } catch (IOException e) {
            System.out.println("Couldn't write key.");
            e.printStackTrace();
        }
    }

}
