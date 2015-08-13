package de.btu.openinfra.backend.db.daos;

import java.util.Locale;
import java.util.UUID;

import de.btu.openinfra.backend.db.MappingResult;
import de.btu.openinfra.backend.db.jpa.model.AttributeType;
import de.btu.openinfra.backend.db.jpa.model.AttributeTypeGroup;
import de.btu.openinfra.backend.db.jpa.model.AttributeTypeToAttributeTypeGroup;
import de.btu.openinfra.backend.db.pojos.AttributeTypeGroupToAttributeTypePojo;

/**
 * This class represents the AttributeTypeGroupToAttributeType and is used to
 * access the underlying layer generated by JPA.
 *
 * @author <a href="http://www.b-tu.de">BTU</a> DBIS
 *
 */
public class AttributeTypeGroupToAttributeTypeDao extends
	OpenInfraValueValueDao<AttributeTypeGroupToAttributeTypePojo,
	AttributeTypeToAttributeTypeGroup, AttributeType, AttributeTypeGroup> {

	/**
	 * This is the required constructor which calls the super constructor and in
	 * turn creates the corresponding entity manager.
	 *
	 * @param currentProjectId the current project id (this should be null when
	 *                         the system schema is selected)
	 * @param schema           the required schema
	 */
	public AttributeTypeGroupToAttributeTypeDao(
			UUID currentProjectId,
			OpenInfraSchemas schema) {
		super(currentProjectId,
				schema,
				AttributeTypeToAttributeTypeGroup.class,
				AttributeType.class,
				AttributeTypeGroup.class);
	}

	@Override
	public AttributeTypeGroupToAttributeTypePojo mapToPojo(
			Locale locale,
			AttributeTypeToAttributeTypeGroup at) {
		AttributeTypeGroupToAttributeTypePojo pojo =
				new AttributeTypeGroupToAttributeTypePojo();
		if(at != null) {
			pojo.setAttributeTypeId(at.getAttributeType().getId());
			pojo.setAttributeTypeGroup(
					AttributeTypeGroupDao.mapToPojoStatically(
							locale,
							at.getAttributeTypeGroup()));
			pojo.setDefaultValue(ValueListValueDao.mapToPojoStatically(
					locale,
					at.getValueListValue()));
			pojo.setMultiplicity(MultiplicityDao.mapToPojoStatically(
					at.getMultiplicityBean()));
			pojo.setOrder(at.getOrder());
			pojo.setUuid(at.getId());
			pojo.setTrid(at.getXmin());
			return pojo;
		} else {
			return null;
		} // end if else
	}

	@Override
	public MappingResult<AttributeTypeToAttributeTypeGroup> mapToModel(
			AttributeTypeGroupToAttributeTypePojo pojo,
			AttributeTypeToAttributeTypeGroup atg) {

	    // TODO set the model values

        // return the model as mapping result
        return new MappingResult<AttributeTypeToAttributeTypeGroup>(
                atg.getId(), atg);
	}

}
