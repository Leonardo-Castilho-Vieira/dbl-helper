package com.db.legends.model;

import java.util.List;

public class DBLCharacter {

	//Status 
	private List<Status> statusVS;
	
	private List<Tag> tags;
	
	private String charName;
	
	private int charId;
	
	
	List<Status> statusByChallengerTeam(List<DBLCharacter> challengerTeam) {
		challengerTeam.forEach(challenger -> {
			// Pegar as cores e tags
			challenger.getTags().forEach(tag -> {
				if("".equals(tag.getTagName())) {
					//this.zAbility().applyBonus();
					//Em vez disso, pegar a porcentagem do que aumentou e que para qual atributo, e ir somando
					//Assim como os debuffs
					
	//					Status status = new Status(this.getStatus());
	//					status.setBonus(this.getAbilties())
					
				}
			});
		});
		
		return null;
	}

	void increaseAlliedStatus(DBLCharacter allied) {
	
	}
	
	List<Status> increseOwnStatus(List<DBLCharacter> challengers, List<DBLCharacter> allies, Buff specialBuff) {
		List<Status> statusByChallenger = statusByChallengerTeam(challengers);
	
		return statusByChallenger;
	}
	
	Status totalNormalStatus() {
		//Base status + Soul Status
		//+ Z Ability 
		return null;
	}
	
	void limiteBreak(int lvl) {
	
		switch(lvl) {
			case 7:
			case 6:
				//5%
			case 5:
			case 4:
				//5%
			case 3:
			case 2:
				//5%
			case 1:
				//5%
			default:
				break;
			
		}
	}

	public List<Status> getStatusVS() {
		return statusVS;
	}

	public void setStatusVS(List<Status> statusVS) {
		this.statusVS = statusVS;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public String getCharName() {
		return charName;
	}

	public void setCharName(String charName) {
		this.charName = charName;
	}

	public int getCharId() {
		return charId;
	}

	public void setCharId(int charId) {
		this.charId = charId;
	}
	
	@Override
	public String toString() {
		final StringBuilder tags = new StringBuilder();
		this.tags.forEach(tag -> {
			tags.append("\n\tTag: " + tag.getTagName() + " - ID: " + tag.getTagId());
		});
		
		return "\nID: " + this.getCharId() + " Nome: " + this.getCharName().toUpperCase() + "\n" + tags.toString();	
	}

}
