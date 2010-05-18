package br.upe.dsc.pso;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ChartDirector.ChartViewer;
import br.upe.dsc.pso.algorithm.GlobalBestPSO;
import br.upe.dsc.pso.algorithm.LocalBestPSO;
import br.upe.dsc.pso.algorithm.PSO;
import br.upe.dsc.pso.problems.PeaksProblem;
import br.upe.dsc.pso.problems.IProblem;
import br.upe.dsc.pso.problems.Problem6;
import br.upe.dsc.pso.problems.RandomPeaksProblem;
import br.upe.dsc.pso.view.ChartView;
import br.upe.dsc.pso.view.SwarmObserver;

/**
 * @author marlon
 * 
 */
public class Main {

	public static void main(String[] args) {
		// IProblem problem = new Problem1();
		// IProblem problem = new Problem2();
		// IProblem problem = new Problem3();
		// IProblem problem = new Problem4();
		// IProblem problem = new Problem5();
		// IProblem problem = new Problem6();
//		 IProblem problem = new PeaksProblem();
		 IProblem problem = new RandomPeaksProblem();
		int swarmSize = 30;
		SwarmObserver swarmObserver = new SwarmObserver(swarmSize, problem);

//		 GlobalBestPSO pso = new GlobalBestPSO(swarmSize,100, 0.01, problem, 0.5, 0.8, swarmObserver);
		LocalBestPSO pso = new LocalBestPSO(swarmSize, 100, 0.01, problem, 2.0, 2.0, swarmObserver);

//		 runSimple(pso);
		runChart(pso);
	}

	private static void runSimple(PSO pso) {
		IProblem problem = new RandomPeaksProblem();
		int swarmSize = 100;
		
		for (int i = 0; i < 30; i++) {
			pso.run();
			SwarmObserver swarmObserver = new SwarmObserver(swarmSize, problem);
			pso = new LocalBestPSO(swarmSize, 100, 0.01, problem, 0.5, 0.8, swarmObserver);
		}
	}

	// Allow this module to run as standalone program for easy testing
	public static void runChart(PSO pso) {

		// Create and set up the main window
		JFrame frame = new JFrame("PSO");
		frame.getContentPane().setBackground(Color.white);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Instantiate an instance of this chart
		ChartView chart = new ChartView();
		chart.setSwarmObserver(pso.getSwarmObserver());
		chart.setFrame(frame);

		// Create the chart and put them in the content pane
		chart.setViewer(new ChartViewer());
		chart.createChart(chart.getViewer(), 0, chart.getSwarmObserver());
		frame.getContentPane().add(chart.getViewer());

		// Display the window
		frame.pack();
		frame.setVisible(true);

		chart.setRunning(true);
		new Thread(chart).start();

		pso.run();
		chart.setRunning(false);
	}

}
