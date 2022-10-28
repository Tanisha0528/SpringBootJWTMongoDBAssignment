package com.Sprinboot.JWT.MongoDB.SprinbootJWTMongoDB.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenService {

    public static final String TokenSecret="sbdv68725e";

    public String createToken(ObjectId id)
    {
        try{
            //random string generated using tokensecret
            //we r using HMAC256 algo
            Algorithm algo= Algorithm.HMAC256(TokenSecret);
            //we r using claims of id and created date using date object
            String token= JWT.create().
                    withClaim("id",id.toString()).
                    withClaim("createdAt",new Date()).
                    sign(algo);
            return token;
        }
        catch(UnsupportedEncodingException e)
        {
                  e.printStackTrace();
        }
        catch(JWTCreationException e)
        {

            e.printStackTrace();
        }
        return null;
    }
    public String getUserIdFromToken(String token){
        try{
            //random string generated using tokensecret
            //we r using HMAC256 algo
            Algorithm algo= Algorithm.HMAC256(TokenSecret);
            //we r using claims of id and created date using date object
            JWTVerifier verifier=JWT.require(algo).build();
            DecodedJWT decodedJWT=verifier.verify(token);
            return decodedJWT.getClaim("id").asString();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch(JWTCreationException e)
        {

            e.printStackTrace();
        }
        return null;}

    public boolean isTokenValid(String token)
    {

        String userid=this.getUserIdFromToken(token);
        return userid!=null;
    }
}
