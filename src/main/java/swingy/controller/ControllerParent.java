package swingy.controller;

public abstract class ControllerParent {

	private static InterfaceMode interfaceMode =  InterfaceMode.TERMINAL;

	public ControllerParent() {}

	protected void switchInterfaceMode(){
		interfaceMode = (interfaceMode == InterfaceMode.SWING) ? InterfaceMode.TERMINAL : InterfaceMode.SWING;
	}

	protected InterfaceMode getInterfaceMode() {
		return interfaceMode;
	}
}
