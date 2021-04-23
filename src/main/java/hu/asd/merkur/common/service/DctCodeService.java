package hu.asd.merkur.common.service;

import org.springframework.stereotype.Service;

import hu.asd.merkur.common.persist.entity.DctCode;
import hu.asd.merkur.common.persist.entity.QDctCode;
import hu.asd.merkur.common.persist.repository.DctCodeRepository;
import hu.asd.merkur.core.service.ServiceParent;

@Service
public class DctCodeService extends ServiceParent<DctCode, QDctCode, DctCodeRepository> {

}
