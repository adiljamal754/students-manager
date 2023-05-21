package model;

import java.io.Serializable;

public class Student implements Serializable,  Comparable<Student> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String  name;
    private double test1, test2;
    
    public Student() {
    	
    }
    
	public Student(int code, String name, double test1, double test2) {
		super();
		this.code = code;
		this.name = name;
		this.test1 = test1;
		this.test2 = test2;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTest1() {
		return test1;
	}

	public void setTest1(double test1) {
		this.test1 = test1;
	}

	public double getTest2() {
		return test2;
	}

	public void setTest2(double test2) {
		this.test2 = test2;
	}
	
	public double caculateAverage() {
		return (test1 + test2)/2;
	}

	public int compareTo(Student o) {
		if(this.code > o.code) return 1;
		if(this.code < o.code) return -1;
		
		return 0;
	}
	
	
	
	
}
