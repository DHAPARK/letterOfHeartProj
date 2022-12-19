package com.project.letterOfHeart.dto;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Repository
@RequiredArgsConstructor
public class ApplicationNaverSENS {
	String accessKey = "vQ4Jm2bC5UV0D2hrYKK3";
	String secretKey = "lWhTEO5EHRVvwTpaDQC1stD9hbQxxMvsXhmQzPel";
	String serviceId = "ncp:sms:kr:298144744141:sms_practice";
	
}
