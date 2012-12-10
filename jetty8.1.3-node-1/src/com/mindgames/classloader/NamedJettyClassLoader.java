package com.mindgames.classloader;

import java.io.IOException;

import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import com.tc.object.bytecode.hook.impl.ClassProcessorHelper;
import com.tc.object.loaders.NamedClassLoader;

public class NamedJettyClassLoader extends WebAppClassLoader {
	
	
	public NamedJettyClassLoader(ClassLoader classLoader, WebAppContext webAppContext)
			throws IOException {
		super(classLoader, webAppContext);
		NamedClassLoader ncl = (NamedClassLoader)this;
		ncl.__tc_setClassLoaderName("NamedJettyClassLoader");
		ClassProcessorHelper.registerGlobalLoader(ncl);

	}

	public NamedJettyClassLoader(WebAppContext webAppContext) throws IOException {
		super(webAppContext);
		NamedClassLoader ncl = (NamedClassLoader)this;
		ncl.__tc_setClassLoaderName("NamedJettyClassLoader");
		ClassProcessorHelper.registerGlobalLoader(ncl);
	}
}
