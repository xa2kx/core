package de.btu.openinfra.backend.db.jpa.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the attribute_value_geomz database table.
 *
 */
@Entity
@Table(name="attribute_value_geomz")
@NamedQueries({
    @NamedQuery(name="AttributeValueGeomz.findAll",
            query="SELECT a FROM AttributeValueGeomz a"),
    @NamedQuery(name="AttributeValueGeomz.findByTopicInstance",
            query="SELECT a "
                    + "FROM AttributeValueGeomz a "
                    + "WHERE a.topicInstance = :value")})
@NamedNativeQueries({
    @NamedNativeQuery(name="AttributeValueGeomz.select",
            query="SELECT %s "
                    + "FROM attribute_value_geomz "
                    + "WHERE id = cast(? as uuid)"),
    @NamedNativeQuery(name="AttributeValueGeomz.insert",
            query="INSERT INTO attribute_value_geomz ("
                    + "attribute_type_to_attribute_type_group_id, "
                    + "topic_instance_id, geom) "
                    + "VALUES (?, ?, %s(?))"),
    @NamedNativeQuery(name="AttributeValueGeomz.update",
            query="UPDATE TABLE attribute_value_geomz SET "
                    + "attribute_type_to_attribute_type_group_id = ?, "
                    + "topic_instance_id = ?, "
                    + "geom = %s(?) WHERE id = ?")
})

public class AttributeValueGeomz implements Serializable, OpenInfraModelObject {
	private static final long serialVersionUID = 1L;

	@Id
	private UUID id;
	
	private Integer xmin;

	private String geom;

	//bi-directional many-to-one association to AttributeTypeToAttributeTypeGroup
	@ManyToOne
	@JoinColumn(name="attribute_type_to_attribute_type_group_id")
	private AttributeTypeToAttributeTypeGroup attributeTypeToAttributeTypeGroup;

	//bi-directional many-to-one association to TopicInstance
	@ManyToOne
	@JoinColumn(name="topic_instance_id")
	private TopicInstance topicInstance;

	public AttributeValueGeomz() {
	}

	@Override
    public UUID getId() {
		return this.id;
	}

	@Override
	public void setId(UUID id) {
		this.id = id;
	}

	public String getGeom() {
		return this.geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public AttributeTypeToAttributeTypeGroup getAttributeTypeToAttributeTypeGroup() {
		return this.attributeTypeToAttributeTypeGroup;
	}

	public void setAttributeTypeToAttributeTypeGroup(AttributeTypeToAttributeTypeGroup attributeTypeToAttributeTypeGroup) {
		this.attributeTypeToAttributeTypeGroup = attributeTypeToAttributeTypeGroup;
	}

	public TopicInstance getTopicInstance() {
		return this.topicInstance;
	}

	public void setTopicInstance(TopicInstance topicInstance) {
		this.topicInstance = topicInstance;
	}
	
	@Override
	public Integer getXmin() {
		return xmin;
	}

}