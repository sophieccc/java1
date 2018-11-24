package SportsClub;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClubManager {
	
	public static final int NAME_INDEX = 0;
	public static final int ADDRESS_INDEX = 1;
	public static final int YEAR_OF_REG_INDEX = 2;
	public static final int EMAIL_INDEX = 3;
	public static int groupid = 101;
	
	public static void main(String[] args) {
		ArrayList<ClubMember> clubMemberList = new ArrayList<ClubMember>();
		ArrayList<Group> groupList = new ArrayList<Group>();
		try {
		FileReader fileReader = new FileReader("members.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		boolean endOfFileFound = false;
		while (!endOfFileFound)
		{
			String memberData = bufferedReader.readLine();
			if (memberData ==null)
			{
				endOfFileFound = true;
			}
			else 
			{
				String[] memberProperties = memberData.split(",");
				ClubMember clubMember = new ClubMember(memberProperties[NAME_INDEX],memberProperties[ADDRESS_INDEX],
						Integer.parseInt(memberProperties[YEAR_OF_REG_INDEX]), memberProperties[EMAIL_INDEX]);
				clubMemberList.add(clubMember);
			}
		}
		bufferedReader.close();
		fileReader.close();
		}
		catch (FileNotFoundException exception) {
			// TODO Auto-generated catch block
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean exit = false;
		Scanner userInput = new Scanner(System.in);
		ClubManager manager = new ClubManager();
		while(!exit)
		{
			System.out.println("Please select 1 to create a group, 2 to view club members, 3 to view details of a member......");
			// User has selected Create a group and provided a group description:
			String groupDescription = "spinning";
			Group group = manager.CreateGroup(groupid++, groupDescription);
			groupList.add(group);
			
			// User has selected to view club members by name
			manager.printMembers(clubMemberList);
			
			// User has selected change user address or email
			String name = "Nayeon Im";
			ClubMember member = manager.getMember(clubMemberList, name);
			String address = "Joly Theatre, TCD";
			String email = null;
			manager.updateMember(member, address, email);
			
		}
	}
	
	public void printMembers(ArrayList<ClubMember> clubMembersList)
	{
		String clubMemberNames = "The names of members of the club are: ";
		for (int i = 0; i < clubMembersList.size(); i++)
		{
			clubMemberNames += clubMembersList.get(i).getName() + ", ";
		}
		System.out.println(clubMemberNames);
	}
	
	public ClubMember getMember(ArrayList<ClubMember> clubMembersList, String name)
	{
		int i = 0;
		ClubMember member = null;
		boolean memberFound = false;
		while (i < clubMembersList.size() && !memberFound)
		{
			member = clubMembersList.get(i);
			if (clubMembersList.get(i).getName().equals(name))
			{
				memberFound = true;
			}
			else
			{
				i++;
				member = null;
			}
		}
		return member;
	}	
	public void updateMember(ClubMember member, String address, String email)
	{
		if(member!=null)
		{
			if (address!=null)
			{
				member.setAddress(address);
			}
			if (email!=null)
			{
				member.setEmail(email);
			}
		}
	}
	public Group CreateGroup (int groupid, String groupDescription)
	{
		Group clubGroup = new Group(groupid, groupDescription, new ArrayList<ClubMember>());
		return clubGroup;
	}
	public void addMemberToGroup(Group group, ClubMember member)
	{
		ArrayList<ClubMember> groupMembers = group.getGroupMembers();
		boolean memberFound = false;
		int counter = 0;
		while (!memberFound && counter < groupMembers.size())
		{
			if (groupMembers.get(counter).getName().equals(member.getName()))
			{
				memberFound = true;
			}
			else
			{
				counter++;
			}
		}
		if (memberFound)
		{
			groupMembers.add(member);
		}
	}
	public void printGroupMembers(Group group)
	{
		ArrayList<ClubMember> membersList = group.getGroupMembers();
		if (membersList !=null && membersList.size() > 0)
		{
			System.out.println("Members of group " + group.getGroupDescription() + "are: ");
			for (ClubMember member:membersList) {
				System.out.println(member.getName());
			}
		}
	}
	public void printGroupNamesAndNumbersOfMembers (ArrayList<Group> groupList)
	{
		if (groupList !=null && groupList.size() > 0)
		{
			for (Group group:groupList)
			{
				System.out.println("Group description: " + group.getGroupDescription() + ", Number of Members" + group.getGroupMembers().size());
			}
		}
	}
}
