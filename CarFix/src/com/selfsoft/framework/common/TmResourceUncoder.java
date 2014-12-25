package com.selfsoft.framework.common;

import net.jcreate.e3.tree.UncodeException;
import net.jcreate.e3.tree.UserDataUncoder;

import com.selfsoft.secrity.model.TmResource;


public class TmResourceUncoder implements UserDataUncoder {
	public Object getID(Object pUserData) throws UncodeException {
		TmResource tmResource = (TmResource)pUserData;
		return tmResource.getId();
	} 
	public Object getParentID(Object pUserData) throws UncodeException {
		TmResource tmResource = (TmResource)pUserData;
		return tmResource.getParentId();
	}
	
}
