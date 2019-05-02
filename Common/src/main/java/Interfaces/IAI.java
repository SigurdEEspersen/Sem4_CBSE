/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import data.Node;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author finch
 */
public interface IAI {

    void setNodes();

    void setBlocks(int[][] blocksArray);

    List<Node> findPath();

    List<Node> getPath(Node currentNode);

    void addAdjacentNodes(Node currentNode);

    void addAdjacentLowerRow(Node currentNode);

    void addAdjacentMiddleRow(Node currentNode);

    void addAdjacentUpperRow(Node currentNode);

    void checkNode(Node currentNode, int col, int row, int cost);

    boolean isFinalNode(Node currentNode);

    boolean isEmpty(PriorityQueue<Node> openList);

    void setBlock(int row, int col);

    Node getInitialNode();

    void setInitialNode(Node initialNode);

    Node getFinalNode();

    void setFinalNode(Node finalNode);

    Node[][] getSearchArea();

    void setSearchArea(Node[][] searchArea);

    PriorityQueue<Node> getOpenList();

    void setOpenList(PriorityQueue<Node> openList);

    Set<Node> getClosedSet();

    void setClosedSet(Set<Node> closedSet);

    int getHvCost();

    void setHvCost(int hvCost);

    int getDiagonalCost();

    void setDiagonalCost(int diagonalCost);

}
