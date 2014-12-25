package com.selfsoft.secrity.service.impl;

import net.jcreate.e3.tree.UncodeException;
import net.jcreate.e3.tree.UserDataUncoder;

import com.selfsoft.secrity.model.TmResource;
/**
 * 
 * @author BaiJX  Jan 13, 2010
 * 业务数据解码器，从业务数据中分解出id和parentid
 */
public class UserDataUncoderImpl implements UserDataUncoder{

	public Object getID(Object pUserData) throws UncodeException {
		TmResource tmResource = (TmResource)pUserData;
		return tmResource.getId();
	} 
	public Object getParentID(Object pUserData) throws UncodeException {
		TmResource tmResource = (TmResource)pUserData;
		return tmResource.getParentId();
	}

}
