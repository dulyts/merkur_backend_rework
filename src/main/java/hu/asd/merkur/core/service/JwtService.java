package hu.asd.merkur.core.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import hu.asd.merkur.common.persist.entity.User;
import hu.asd.merkur.core.service.dto.JwtPayload;

@Service
public class JwtService {

	@Value("${merkur.auth.jwt.secret}")
	private String jwtTokenSecret;

	public String createJwt(User user, UUID userRoleId) {
		List<String> urs = user.getRoles().stream()//
				.map(ur -> ur.getId().toString())//
				.collect(Collectors.toList());
		Algorithm algorithm = Algorithm.HMAC256(jwtTokenSecret);
		String token = JWT//
				.create()//
				.withIssuer("merkur")//
				.withIssuedAt(new Date())//
				.withClaim("userId", user.getId().toString())//
				.withClaim("name", user.getName())//
				.withClaim("neptun", user.getNeptun())//
				.withClaim("email", user.getEmail())//
				.withClaim("gender", user.getGender().getCode())//
				.withClaim("currentUserRoleId", userRoleId.toString())//
				.withClaim("userRoles", urs)//
				.sign(algorithm);
		return token;
	}

	public JwtPayload getValidUserDataFromJwt(String jwt) throws SignatureVerificationException {
		Algorithm algorithm = Algorithm.HMAC256(jwtTokenSecret);
		DecodedJWT decoded = JWT.decode(jwt);
		algorithm.verify(decoded);

		List<String> urs = decoded.getClaim("userRoles").asList(String.class);
		return new JwtPayload()//
				.setUserId(decoded.getClaim("userId").asString())//
				.setName(decoded.getClaim("name").asString())//
				.setNeptun(decoded.getClaim("neptun").asString())//
				.setEmail(decoded.getClaim("email").asString())//
				.setGender(decoded.getClaim("gender").asString())//
				.setCurrentUserRoleId(decoded.getClaim("currentUserRoleId").asString())//
				.setUserRoles(urs);
	}

}
