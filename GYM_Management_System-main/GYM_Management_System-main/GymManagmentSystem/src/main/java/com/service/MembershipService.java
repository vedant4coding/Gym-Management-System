package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import comm.model.Membership;

@Service
public class MembershipService implements MembershipServiceInf{
	List<Membership>  memberships=new ArrayList<>();

	@Override
	public void addMembership(Membership membership) {
		try
		{
			boolean check= memberships.add(membership);
			if(check)
			{
				System.out.println("New Membership added Sucessfull"+membership+"");;
			}
			else
			{
				System.out.println("Member not added some problem is there");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Member not added exception arrise");
			
		}
		
		
	}

	@Override
	public Optional<Membership> getMembershipById(int id) {
		try
		{
			return memberships.stream().filter(memship -> memship.getId()==id).findFirst();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Membership> getAllMemberships() {
		
		return new ArrayList<Membership>(memberships);
	}

	@Override
	public String updateMembership(Membership membership) {
		try
		{
			getMembershipById(membership.getId()).ifPresent(existing->
			{
				
				existing.setStartDate(membership.getStartDate());
				existing.setType(membership.getType());
				if(membership.getType().equals("Monthly"))
				{
					existing.setEndDate(1);
				}
				else if(membership.getType().equals("Quarterly"))
				{
					existing.setEndDate(3);
				}
				else
				{
					existing.setEndDate(12);
				}
				

			});
			return "membership added Sucessfull";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "membership not added Some problem is there..........";
		}
		
	}

	@Override
	public String deleteMembership(int id) {
		
		try
		{
			boolean check=memberships.removeIf(m -> m.getId()==id);
			if(check)
			    return "Membership deleted by id"+id;
			else
				return "Membershipnot Found";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Membership Not Found by id"+id;
		}
		
		
		
	}

}
