package hu.asd.merkur.core.configuration;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import hu.asd.merkur.common.persist.entity.Dormitory;
import hu.asd.merkur.common.persist.entity.Role;
import lombok.Data;

@Configuration
public class MerkurRequestContext {

	@Bean("merkur")
	@RequestScope
	public MerkurRequestContextHolder createMerkurRequestContextHolder(HttpServletRequest request) {
		return new MerkurRequestContextHolder();
//		try {
//			MerkurRequestContextHolder ctxHolder = new MerkurRequestContextHolder();
//
//			String userRoleId = request.getHeader("merkur-user-role-id");
//			if (StringUtil.isEmpty(userRoleId)) {
//				return null;
//			}
//			UserRole ur = userRoleRepo.findById(UUID.fromString(userRoleId)).orElseThrow();
//			ctxHolder.setDormitory(ur.getDormitory());
//			ctxHolder.setRole(ur.getRole());
//			return ctxHolder;
//		} catch (Exception e) {
//			return null;
//		}
	}

	@Data
	public class MerkurRequestContextHolder {

		private Dormitory dormitory;
		private Role role;

		public UUID currentDormitory() {
			return dormitory.getId();
		}

	}

}
