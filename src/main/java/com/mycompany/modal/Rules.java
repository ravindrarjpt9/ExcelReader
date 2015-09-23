package com.mycompany.modal;

public class Rules {

	private String groupName;
	private String rule;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	@Override
	public String toString() {
		
		return "Group Name :"+groupName+" Rule :"+rule+"\n";
	}
}
