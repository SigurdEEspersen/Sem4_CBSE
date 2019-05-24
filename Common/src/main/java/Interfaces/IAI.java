package Interfaces;

import data.State;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gruppe 11
 */
public interface IAI {

    void CalculateKeys();

    void init(int sX, int sY, int gX, int gY);

    State calculateKey(State u);

    double getRHS(State u);

    double getG(State u);

    double heuristic(State a, State b);

    double eightCondist(State a, State b);

    boolean replan();

    int computeShortestPath();

    LinkedList<State> getSucc(State u);

    LinkedList<State> getPred(State u);

    void updateStart(int x, int y);

    void updateGoal(int x, int y);

    void updateVertex(State u);

    boolean isValid(State u);

    void setG(State u, double g);

    void setRHS(State u, double rhs);

    void makeNewCell(State u);

    void updateCell(int x, int y, double val);

    void insert(State u);

    float keyHashCode(State u);

    boolean occupied(State u);

    double trueDist(State a, State b);

    double cost(State a, State b);

    boolean close(double x, double y);

    List<State> getPath();

}
