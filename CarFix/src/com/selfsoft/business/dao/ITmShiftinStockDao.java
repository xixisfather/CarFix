package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TmShiftinStock;
import com.selfsoft.framework.dao.IDao;

public interface ITmShiftinStockDao extends IDao<TmShiftinStock> {

	public List getTmshiftinStockVos(Long isConfirm , TmShiftinStock tmShiftinStock , Long shiftInId);
}
