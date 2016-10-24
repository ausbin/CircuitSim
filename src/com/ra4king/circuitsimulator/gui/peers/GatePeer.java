package com.ra4king.circuitsimulator.gui.peers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.ra4king.circuitsimulator.gui.ComponentPeer;
import com.ra4king.circuitsimulator.gui.Connection;
import com.ra4king.circuitsimulator.gui.Connection.PortConnection;
import com.ra4king.circuitsimulator.gui.GuiUtils;
import com.ra4king.circuitsimulator.simulator.CircuitState;
import com.ra4king.circuitsimulator.simulator.components.gates.Gate;

/**
 * @author Roi Atalla
 */
public class GatePeer extends ComponentPeer<Gate> {
	private List<Connection> connections = new ArrayList<>();
	
	public GatePeer(Gate gate, int x, int y) {
		super(gate, x, y, 3 * GuiUtils.BLOCK_SIZE, 2 * ((gate.getNumPorts() + 1) / 2) * GuiUtils.BLOCK_SIZE);
		
		int gates = gate.getNumPorts() - 1;
		for(int i = 0; i < gates; i++) {
			int add = (gates % 2 == 0 && i >= gates / 2) ? 2 : 1;
			connections.add(new PortConnection(this, gate.getPort(i), 0, (i + add) * GuiUtils.BLOCK_SIZE));
		}
		
		connections.add(new PortConnection(this, gate.getPort(gates), getWidth(), (gates / 2 + 1) * GuiUtils.BLOCK_SIZE));
	}
	
	@Override
	public List<Connection> getConnections() {
		return connections;
	}
	
	@Override
	public void paint(Graphics2D g, CircuitState circuitState) {
		g.setColor(Color.BLACK);
		GuiUtils.drawShape(g::drawRect, this);
		g.drawString(getComponent().toString(), getX() + 2, getY() + getHeight() / 2 + 5);
	}
}