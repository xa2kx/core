package de.btu.openinfra.backend.db.daos;

import java.util.Locale;
import java.util.UUID;

import de.btu.openinfra.backend.db.MappingResult;
import de.btu.openinfra.backend.db.OpenInfraSchemas;
import de.btu.openinfra.backend.db.jpa.model.RelationshipType;
import de.btu.openinfra.backend.db.jpa.model.RelationshipTypeToTopicCharacteristic;
import de.btu.openinfra.backend.db.jpa.model.TopicCharacteristic;
import de.btu.openinfra.backend.db.pojos.TopicCharacteristicToRelationshipTypePojo;
import de.btu.openinfra.backend.exception.OpenInfraEntityException;
import de.btu.openinfra.backend.exception.OpenInfraExceptionTypes;

/**
 * This class represents the TopicCharacteristicToRelationshipType and is used
 * to access the underlying layer generated by JPA.
 *
 * @author <a href="http://www.b-tu.de">BTU</a> DBIS
 *
 */
public class TopicCharacteristicToRelationshipTypeDao
	extends OpenInfraValueValueDao<TopicCharacteristicToRelationshipTypePojo,
	RelationshipTypeToTopicCharacteristic, RelationshipType,
	TopicCharacteristic> {

    /**
     * This is the required constructor which calls the super constructor and in
     * turn creates the corresponding entity manager.
     *
     * @param currentProjectId the current project id (this should be null when
     *                         the system schema is selected)
     * @param schema           the required schema
     */
	public TopicCharacteristicToRelationshipTypeDao(
			UUID currentProjectId,
			OpenInfraSchemas schema) {
		super(currentProjectId, schema,
				RelationshipTypeToTopicCharacteristic.class,
				RelationshipType.class, TopicCharacteristic.class);
	}

	@Override
	public TopicCharacteristicToRelationshipTypePojo mapToPojo(
			Locale locale,
			RelationshipTypeToTopicCharacteristic rtttc) {
		return mapToPojoStatically(locale, rtttc,
		        new MetaDataDao(currentProjectId, schema));
	}

	@Override
	public MappingResult<RelationshipTypeToTopicCharacteristic> mapToModel(
			TopicCharacteristicToRelationshipTypePojo pojo,
			RelationshipTypeToTopicCharacteristic rtt) {

        // TODO set the model values

        // return the model as mapping result
        return new MappingResult<RelationshipTypeToTopicCharacteristic>(
                rtt.getId(), rtt);
	}

	/**
	 * This method implements the method mapToPojo in a static way.
	 *
	 * @param locale the requested language as Java.util locale
	 * @param rtttc  the model object
	 * @param mdDao  the meta data DAO
	 * @return       the POJO object when the model object is not null else null
	 */
	public static TopicCharacteristicToRelationshipTypePojo mapToPojoStatically(
			Locale locale,
			RelationshipTypeToTopicCharacteristic rtttc,
			MetaDataDao mdDao) {

		try {
			TopicCharacteristicToRelationshipTypePojo pojo =
					new TopicCharacteristicToRelationshipTypePojo(rtttc, mdDao);

			pojo.setRelationshipe(rtttc.getRelationshipType().getId());
			pojo.setMultiplicity(MultiplicityDao.mapToPojoStatically(
					rtttc.getMultiplicityBean(), mdDao));
			pojo.setTopicCharacteristic(
				TopicCharacteristicDao.mapToPojoStatically(
					locale,
					rtttc.getTopicCharacteristic(),
					mdDao));

			return pojo;
		} catch (NullPointerException npe) {
            throw new OpenInfraEntityException(
                    OpenInfraExceptionTypes.MISSING_DATA_IN_POJO);
        }
	}

}
