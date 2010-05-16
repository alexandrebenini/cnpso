package br.upe.dsc.pso.problems;

public class Problem3 implements IProblem {
    
    public int getDimensionsNumber() {
            return 6;
    }
    
    public double getLowerLimit(int dimension) {
            if (dimension == 5) {
                    return MINIMUM_DIMENSION_VALUE;
            }
            return -5.12;
    }
    
    public double getUpperLimit(int dimension) {
            return 5.12;
    }
    
    public boolean compareFitness(Double pBestFitness, Double currentPositionFitness) {
            return currentPositionFitness > pBestFitness;
    }
    
    public double getFitness(Double... dimension) {
            double result = 0;
            for (int i = 0; i < dimension.length - 1; i++){
                    result += Math.floor(dimension[i]);
            }
            return result;
    }
}
