package edu.istu.logistics.algorithm.branchAndBound;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;


public class BranchAndBoundEngine {
    private static final int M = -1;
    private static final String SYMBOL = "M";

    public Stack<Integer> start(double[][] distanceMatrix) {
        long start = System.currentTimeMillis();
        Stack<Integer> stack = new Stack<>();
        System.out.println("Read graph to file:");
        double[][] matrix = distanceMatrix;
        double[][] clone = clone(matrix);
        List<Integer> v = new ArrayList<>();
        for (int i = 1; i <= matrix.length; i++) {
            v.add(i);
        }
        printMatrix(matrix);
        int count = 1;
        while (matrix.length > 1) {
            System.out.println("\n##########################################");
            System.out.println("STAGE #" + count++ + ":");
            double[] di = getMinArray(matrix, false);
            matrix = diffMatrix(matrix, di, false);
            double[] dj = getMinArray(matrix, true);
            matrix = diffMatrix(matrix, dj, true);
            System.out.println("\ndi: " + Arrays.toString(di) + ";");
            System.out.println("dj: " + Arrays.toString(dj) + ";");
            System.out.println("\nMatrix after diff:");
            printMatrix(matrix);
            matrix = getPath(matrix, stack, v);
            System.out.println("\nReduction matrix:");
            printMatrix(matrix);
            if (matrix.length == 1) {
                push(stack, v.remove(0));
            }
            System.out.print("Path now: ");
            printStack(stack);
        }
        if (!stack.empty()) {
            stack.push(stack.get(0));
        }
        System.out.println("\n##########################################");
        System.out.println("\nAnswer:");
        System.out.print("Path: ");
        printStack(stack);
        Stack<Integer>resultStack = new Stack<>();
        resultStack.addAll(stack);
        System.out.println("Sum:  " + getSum(stack, clone));
        start = System.currentTimeMillis() - start;
        System.out.printf("Spent time: %d.%s sec.;", start / 1000, new DecimalFormat("000").format(start % 1000));
        return resultStack;
    }

    private double[][] clone(double[][] martrix) {
        double[][] clone = new double[martrix.length][];
        int count = 0;
        for (double[] line : martrix) {
            clone[count++] = line.clone();
        }
        return clone;
    }

    private double getSum(Stack<Integer> stack, double[][] clone) {
        double sum = 0;
        if (!stack.empty()) {
            int v = stack.pop();
            while (!stack.empty()) {
                sum += clone[v - 1][stack.peek() - 1];
                v = stack.pop();
            }
        }
        return sum;
    }

    private boolean isNumber(String number) {
        return number != null;
    }

    private void printStack(Stack<Integer> stack) {
        StringBuilder sb = new StringBuilder();
        if (!stack.empty()) {
            for (Integer num : stack) {
                sb.append(num).append(" -> ");
            }
            sb.delete(sb.length() - 4, sb.length());
        }
        System.out.println(sb.toString());
    }

    private double[][] getPath(double[][] matrix, Stack<Integer> stack, List<Integer> v) {
        int indexI = 0;
        int indexJ = 0;
        double max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    double sum = getMin(matrix, i, false, j) + getMin(matrix, j, true, i);
                    if (sum > max) {
                        max = sum;
                        indexI = i;
                        indexJ = j;
                    }
                }
            }
        }
        matrix[indexJ][indexI] = M;
        matrix = removeLineAndRow(matrix, indexI, indexJ);
        push(stack, v.get(indexI));
        push(stack, v.get(indexJ));
        v.remove(indexI);
        return matrix;
    }

    private void push(Stack<Integer> stack, int v) {
        if (stack.search(v) == -1) {
            stack.push(v);
        }
    }

    private double[][] removeLineAndRow(double[][] matrix, int indexI, int indexJ) {
        double[][] result = new double[matrix.length - 1][matrix.length - 1];
        int countI = 0;
        int countJ;
        for (int i = 0; i < matrix.length; i++) {
            if (i != indexI) {
                countJ = 0;
                for (int j = 0; j < matrix.length; j++) {
                    if (j != indexJ) {
                        result[countI][countJ++] = matrix[i][j];
                    }
                }
                countI++;
            }
        }
        matrix = result;
        return matrix;
    }

    private double getMin(double[][] matrix, int index, boolean row, int j) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (i != j) {
                double number = row ? matrix[i][index] : matrix[index][i];
                if (number != M && number < min) {
                    min = number;
                }
            }
        }
        return min;
    }

    private double[] getMinArray(double[][] matrix, boolean row) {
        double[] res = new double[matrix.length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            res[count++] = getMin(matrix, i, row, -1);
        }
        return res;
    }

    private double[][] diffMatrix(double[][] matrix, double[] d, boolean row) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != M) {
                    matrix[i][j] -= row ? d[j] : d[i];
                }
            }
        }
        return matrix;
    }

    private void printMatrix(double[][] matrix) {
        int length = matrix.length;
        for (double[] line : matrix) {
            System.out.print("[");
            for (int index = 0; index < length; index++) {
                System.out.print(line[index] == M ? SYMBOL : line[index]);
                if (index < length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    private double[][] readFile(String path) {
        StringBuilder sb = new StringBuilder();
        try (Scanner read = new Scanner(new File(path))) {
            while (read.hasNextLine()) {
                String line = read.nextLine().trim();
                if (line.length() > 0) {
                    sb.append(line).append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] lines = sb.toString().split("\n");
        int length = lines.length;
        System.out.println("length: " + length);
        double[][] matrix = new double[length][length];
        for (int i = 0; i < length; i++) {
            String[] numbers = lines[i].split("\\s+");
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    matrix[i][j] = M;
                } else if (isNumber(numbers[j])) {
                    matrix[i][j] = Double.parseDouble(numbers[j]);
                }
            }
        }
        return matrix;
    }
}

