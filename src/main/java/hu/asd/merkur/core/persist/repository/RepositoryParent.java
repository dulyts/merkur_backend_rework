package hu.asd.merkur.core.persist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.types.EntityPath;

import hu.asd.merkur.core.persist.entity.AbstractEntity;

@NoRepositoryBean
public interface RepositoryParent<T extends AbstractEntity, Q extends EntityPath<T>>
		extends JpaRepository<T, UUID>, QuerydslPredicateExecutor<T>/* , QuerydslBinderCustomizer<Q> */ {

}
