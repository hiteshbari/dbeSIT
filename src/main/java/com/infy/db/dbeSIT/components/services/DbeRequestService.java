package com.infy.db.dbeSIT.components.services;

import com.infy.db.dbeSIT.components.exceptions.CustomJsonConversionEx;
import com.infy.db.dbeSIT.model.dto.NarConfigDTO;

public interface DbeRequestService {
	public String createNarConfig(NarConfigDTO narConfigDTO) throws CustomJsonConversionEx ;
}
