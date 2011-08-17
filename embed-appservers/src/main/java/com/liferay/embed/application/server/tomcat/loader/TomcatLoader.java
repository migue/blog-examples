package com.liferay.embed.application.server.tomcat.loader;

import java.beans.PropertyChangeListener;

import org.apache.catalina.Container;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Loader;
import org.apache.catalina.util.LifecycleBase;

public class TomcatLoader extends LifecycleBase implements Lifecycle, Loader {

	public TomcatLoader(ClassLoader parentClassLoader) {
		_classloader = new TomcatDelegatingClassloader(
				parentClassLoader,
				new String[] { "/home/migue/development/workspaces/workspace-liferayportal/Archive/portal/portal-impl/classes" });
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
	}

	@Override
	public void addRepository(String arg0) {

	}

	@Override
	public void backgroundProcess() {
		System.out
				.println("-----------------------------------------------------------------");
		System.out.println("PERIODIC TASKS: do reloading . . . . ?????");
		System.out
				.println("-----------------------------------------------------------------");
	}

	@Override
	public String[] findRepositories() {
		// TODO: point to the portal-impl directory ??
		return new String[] {};
	}

	@Override
	public ClassLoader getClassLoader() {
		return _classloader;
	}

	@Override
	public Container getContainer() {
		return _container;
	}

	@Override
	public boolean getDelegate() {
		return _delegate;
	}

	@Override
	public String getInfo() {
		return "Liferay Devel Loader / 1.0";
	}

	@Override
	public boolean getReloadable() {
		return _reloadable;
	}

	@Override
	public boolean modified() {
		return false;
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContainer(Container container) {
		_container = container;
	}

	@Override
	public void setDelegate(boolean delegate) {
		_delegate = delegate;
	}

	@Override
	public void setReloadable(boolean reloadable) {
		_reloadable = reloadable;
	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		_classloader = null;
	}

	@Override
	protected void initInternal() throws LifecycleException {
		// TODO Do nothing at this moment
	}

	@Override
	protected void startInternal() throws LifecycleException {
		fireLifecycleEvent(Lifecycle.START_EVENT, this);
		setState(LifecycleState.STARTING);
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		fireLifecycleEvent(Lifecycle.STOP_EVENT, this);
		setState(LifecycleState.STOPPING);
	}

	private ClassLoader _classloader;
	private Container _container;
	private boolean _delegate;
	private boolean _reloadable;

}
