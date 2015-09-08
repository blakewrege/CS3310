package edu.wmich.cs3310.asgn1;

/**
 * Index node.
 * @author Caleb Viola
 */
public class Node {
	private String code ;
	private int DRP;
	private int link;
	
	/*******************************************
	 * Node constructor. 
	 * @param code 
	 * @param DRP 
	 * @param link 
	 */
	public Node(String code, int DRP, int link){
		this.code = code;
		this.DRP = DRP;
		this.link = link;
	}
	
	/************************
	 * Getter for code.
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/********************
	 * Getter for DRP;
	 * @return the dRP
	 */
	public int getDRP() {
		return DRP;
	}
	
	/******************************
	 * Setter for link.
	 * @param link
	 */
	public void setLink(int link) {
		this.link = link;
	}
	
	/*********************
	 * Getter for link.
	 * @return the link
	 */
	public int getLink() {
		return link;
	}

}
