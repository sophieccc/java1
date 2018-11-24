package SportsClub;

import java.util.ArrayList;

public class Group {
	private int groupid;
	private String groupDescription;
	private ArrayList<ClubMember>groupMembers;
	
	public Group(int groupid, String groupDescription, ArrayList<ClubMember> groupMembers)
	{
		this.groupid = groupid;
		this.groupDescription = groupDescription;
		this.groupMembers = groupMembers;
	}
	
	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public ArrayList<ClubMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(ArrayList<ClubMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

}
