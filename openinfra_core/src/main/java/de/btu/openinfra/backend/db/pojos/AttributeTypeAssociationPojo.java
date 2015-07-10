package de.btu.openinfra.backend.db.pojos;

import java.util.UUID;

public class AttributeTypeAssociationPojo extends OpenInfraPojo {

	private AttributeTypePojo attributeType;
	private UUID associatedId;
	private ValueListValuePojo relationship;
	
	public AttributeTypePojo getAttributeType() {
		return attributeType;
	}
	
	public void setAttributeType(AttributeTypePojo attributeType) {
		this.attributeType = attributeType;
	}
	
	public UUID getAssociatedId() {
		return associatedId;
	}
	
	public void setAssociatedId(UUID associatedId) {
		this.associatedId = associatedId;
	}
	
	public ValueListValuePojo getRelationship() {
		return relationship;
	}
	
	public void setRelationship(ValueListValuePojo relationship) {
		this.relationship = relationship;
	}
	
	
}
