package de.btu.openinfra.backend.db.daos;

import java.util.Locale;
import java.util.UUID;

import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaQuery;

import de.btu.openinfra.backend.db.jpa.model.AttributeTypeToAttributeTypeGroup;
import de.btu.openinfra.backend.db.jpa.model.AttributeValueGeomz;
import de.btu.openinfra.backend.db.jpa.model.TopicInstance;
import de.btu.openinfra.backend.db.pojos.AttributeValueGeomzPojo;

/**
 * This class represents the AttributeValueGeomz and is used to access the
 * underlying layer generated by JPA.
 *
 * @author <a href="http://www.b-tu.de">BTU</a> DBIS
 *
 */
public class AttributeValueGeomzDao
	extends OpenInfraValueDao<AttributeValueGeomzPojo, AttributeValueGeomz,
	            TopicInstance> {

	/**
	 * This variable defines the default geometry type. The default type is
	 * used to implement the default read methods {@see OpenInfraDao}.
	 */
	// TODO delete this
	private AttributeValueGeomType defaultGeomType =
			AttributeValueGeomType.TEXT;

	/**
	 * This variable defines the name of the data type which is specified in the
	 * database.
	 */
	public static final String DATA_TYPE_NAME = "geometry(GeometryZ)";

	/**
	 * This is the required constructor which calls the super constructor and in
	 * turn creates the corresponding entity manager.
	 *
	 * @param currentProjectId the current project id (this should be null when
	 *                         the system schema is selected)
	 * @param schema           the required schema
	 */
	public AttributeValueGeomzDao(
			UUID currentProjectId,
			OpenInfraSchemas schema) {
		super(currentProjectId, schema, AttributeValueGeomz.class,
		        TopicInstance.class);
	}

	/**
	 * This is the required constructor which calls the super constructor and in
	 * turn creates the corresponding entity manager.
	 *
	 * @param currentProjectId the current project id (this should be null when
	 *                         the system schema is selected)
	 * @param schema           the required schema
	 * @param geomType         the required geom type
	 */
	public AttributeValueGeomzDao(
			UUID currentProjectId,
			OpenInfraSchemas schema,
			AttributeValueGeomType geomType) {
		super(currentProjectId, schema, AttributeValueGeomz.class,
		        TopicInstance.class);
		if(geomType != null) {
			defaultGeomType = geomType;
		} // end if
	}

	@Override
	public AttributeValueGeomzPojo mapToPojo(
			Locale locale,
			AttributeValueGeomz avgz) {
	    // get the NamedNativeQuery
        String sqlString = em.createNamedQuery(
                AttributeValueGeomz.class.getSimpleName() + ".select")
                .unwrap(JpaQuery.class).getDatabaseQuery().getSQLString();
        // format the SQL statement for retrieving geometry values
        String queryString = String.format(
                sqlString,
                defaultGeomType.getPsqlFnSignature());

	    // add the id parameter to the query
	    Query qGeom = em.createNativeQuery(queryString);
	    qGeom.setParameter(1, avgz.getId());

        AttributeValueGeomzPojo avgzPojo =
                new AttributeValueGeomzPojo();

        // set the topic instance id
        avgzPojo.setTopicInstanceId(avgz.getTopicInstance().getId());
	    // execute the SQL statement and set the geometry value
        avgzPojo.setGeom(qGeom.getResultList().get(0).toString());
		// set the geometry type
		avgzPojo.setGeomType(defaultGeomType);
		// set the attribute type to attribute type id group of the value
        avgzPojo.setAttributeTypeToAttributeTypeGroupId(
                avgz.getAttributeTypeToAttributeTypeGroup().getId());
		// set the id of the object
		avgzPojo.setUuid(avgz.getId());

		return avgzPojo;
	}

	@Override
	public MappingResult<AttributeValueGeomz> mapToModel(
			AttributeValueGeomzPojo pojo,
			AttributeValueGeomz avgz) {
	    // return null if the pojo is null
        if (pojo != null) {

            // in case the attribute type to attribute type group id, the
            // topic instance id or the geometry is null
            if (pojo.getAttributeTypeToAttributeTypeGroupId() == null ||
                    pojo.getTopicInstanceId() == null ||
                    pojo.getGeom() == null) {
                return null;
            }

            // in case the geometry is an empty string
            if (pojo.getGeom().equals("")) {
                return null;
            }

            // set the textual information
            avgz.setGeom(pojo.getGeom());

            // set the attribute type to attribute type group
            avgz.setAttributeTypeToAttributeTypeGroup(em.find(
                    AttributeTypeToAttributeTypeGroup.class,
                    pojo.getAttributeTypeToAttributeTypeGroupId()));

            // set the topic instance
            avgz.setTopicInstance(
                    em.find(TopicInstance.class, pojo.getTopicInstanceId()));

            return new MappingResult<AttributeValueGeomz>(avgz.getId(), avgz);
        } else {
            return null;
        }
	}

}
