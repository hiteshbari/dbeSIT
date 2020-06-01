package com.infy.db.dbeSIT.components.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.db.dbeSIT.components.repo.NarConfigInfoRepo;
import com.infy.db.dbeSIT.components.repo.NarRolesRepo;
import com.infy.db.dbeSIT.model.dto.NarConfigDTO;
import com.infy.db.dbeSIT.model.entity.NarConfigInfo;
import com.infy.db.dbeSIT.model.entity.NarRoles;
import com.infy.db.dbeSIT.util.DBESITUtil;

@Service
public class DbeRequestServiceImpl implements DbeRequestService{

	@Autowired
	NarConfigInfoRepo narConfigInfoRepo;
	
	@Autowired
	NarRolesRepo narRolesRepo;
	
	@Override
	public String createNarConfig(NarConfigDTO narConfigDTO) {
		String rtn = DBESITUtil.RETURN_DEF;
		if(narConfigInfoRepo.isNarExist(narConfigDTO.getNarId()) > 0) {
			rtn = DBESITUtil.STATUS_ERROR;
		}else {
			NarConfigInfo narConfigInfo = new NarConfigInfo();
			BeanUtils.copyProperties(narConfigDTO, narConfigInfo);
			narConfigInfo = narConfigInfoRepo.saveAndFlush(narConfigInfo);
			int nId = narConfigInfo.getnId();
			createNarRoles(nId,narConfigDTO);
			sendReturnStatus(narConfigDTO);
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
	
	private void sendReturnStatus(NarConfigDTO narConfigDTO) {
		
	}

}
