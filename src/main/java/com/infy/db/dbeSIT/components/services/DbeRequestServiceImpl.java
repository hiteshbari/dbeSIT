package com.infy.db.dbeSIT.components.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infy.db.dbeSIT.components.exceptions.CustomJsonConversionEx;
import com.infy.db.dbeSIT.components.repo.NarConfigInfoRepo;
import com.infy.db.dbeSIT.components.repo.NarRolesRepo;
import com.infy.db.dbeSIT.model.dto.KafkaMsgDTO;
import com.infy.db.dbeSIT.model.dto.NarConfigDTO;
import com.infy.db.dbeSIT.model.dto.StatusInfoDTO;
import com.infy.db.dbeSIT.model.entity.NarConfigInfo;
import com.infy.db.dbeSIT.model.entity.NarRoles;
import com.infy.db.dbeSIT.util.DBESITUtil;

@Service
public class DbeRequestServiceImpl implements DbeRequestService{

	@Autowired
	NarConfigInfoRepo narConfigInfoRepo;
	
	@Autowired
	NarRolesRepo narRolesRepo;
	
	@Autowired
	private Environment env;
	
	@Override
	public String createNarConfig(NarConfigDTO narConfigDTO) throws CustomJsonConversionEx {
		String rtn = DBESITUtil.RETURN_DEF;
		if(narConfigInfoRepo.isNarExist(narConfigDTO.getNarId()) > 0) {
			rtn = DBESITUtil.STATUS_ERROR;
		}else {
			NarConfigInfo narConfigInfo = new NarConfigInfo();
			BeanUtils.copyProperties(narConfigDTO, narConfigInfo);
			narConfigInfo = narConfigInfoRepo.saveAndFlush(narConfigInfo);
			int nId = narConfigInfo.getnId();
			createNarRoles(nId,narConfigDTO);
			
			StatusInfoDTO statusInfoDTO = new StatusInfoDTO();
			statusInfoDTO.setReqId(narConfigDTO.getReqId());
			statusInfoDTO.setNarId(narConfigDTO.getNarId());
			statusInfoDTO.setDbeEnv(DBESITUtil.DBE_ENV_PROD);
			statusInfoDTO.setReqAction(DBESITUtil.DBE_ACT_CONFIG_NAR);
			statusInfoDTO.setActionby(DBESITUtil.DBE_EMAIL_SYSTEM);
			statusInfoDTO.setReqStatus(DBESITUtil.STATUS_OK);
			
			try {
				System.out.println("Waiting for a minute");
				TimeUnit.SECONDS.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendReturnStatus(statusInfoDTO);
			rtn = DBESITUtil.STATUS_OK;
		}
		return rtn;
	}
	
	private String createNarRoles(int nId,NarConfigDTO narConfigDTO) {
		String rtn = DBESITUtil.RETURN_DEF;
		List<NarRoles> listRoles = new ArrayList<NarRoles>();
		for(String pe : narConfigDTO.getPolEditors()) {
			NarRoles nr = new NarRoles();
			nr.setnId(nId);
			nr.setUserEmail(pe);
			nr.setDbeRole(DBESITUtil.DBE_POLICY_EDITOR);
			listRoles.add(nr);
		}
		for(String pa : narConfigDTO.getPolApprovers()) {
			NarRoles nr = new NarRoles();
			nr.setnId(nId);
			nr.setUserEmail(pa);
			nr.setDbeRole(DBESITUtil.DBE_POLICY_APPROVER);
			listRoles.add(nr);
		}
		narRolesRepo.saveAll(listRoles);
		return rtn;
	}
	
	private void sendReturnStatus(StatusInfoDTO statusInfoDTO) throws CustomJsonConversionEx {
		KafkaMsgDTO kafkaMsgDTO = new KafkaMsgDTO();
		String jsonData = DBESITUtil.getDTOtoJson(statusInfoDTO);
		kafkaMsgDTO.setMsgId(statusInfoDTO.getReqId());
		kafkaMsgDTO.setMsgType(DBESITUtil.DBE_MSG_TYPE_STATUS_INFO);
		kafkaMsgDTO.setMsgTo(statusInfoDTO.getDbeEnv());
		kafkaMsgDTO.setMsgBody(jsonData);
		sendMsgToKafka(kafkaMsgDTO);
	}

	private void sendMsgToKafka(KafkaMsgDTO kafkaMsgDTO) throws CustomJsonConversionEx {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		String kafkaUrl = env.getProperty("kafka.prod.url")+"/produce/publishMessage";
		String jsonData = DBESITUtil.getDTOtoJson(kafkaMsgDTO);
		HttpEntity<String> requestEntity = new HttpEntity<String>(jsonData, header);
		ResponseEntity<String> response = new RestTemplate().postForEntity(kafkaUrl, requestEntity, String.class);
	}
}
