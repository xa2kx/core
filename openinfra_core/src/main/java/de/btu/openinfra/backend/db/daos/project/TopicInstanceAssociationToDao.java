package de.btu.openinfra.backend.db.daos.project;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import de.btu.openinfra.backend.db.MappingResult;
import de.btu.openinfra.backend.db.OpenInfraSchemas;
import de.btu.openinfra.backend.db.daos.OpenInfraValueValueDao;
import de.btu.openinfra.backend.db.daos.RelationshipTypeDao;
import de.btu.openinfra.backend.db.jpa.model.RelationshipType;
import de.btu.openinfra.backend.db.jpa.model.TopicCharacteristic;
import de.btu.openinfra.backend.db.jpa.model.TopicInstance;
import de.btu.openinfra.backend.db.jpa.model.TopicInstanceXTopicInstance;
import de.btu.openinfra.backend.db.pojos.project.TopicInstanceAssociationToPojo;
import de.btu.openinfra.backend.exception.OpenInfraEntityException;
import de.btu.openinfra.backend.exception.OpenInfraExceptionTypes;

/**
 * This class represents the TopicInstanceAssociation and is used to access the
 * underlying layer generated by JPA.
 *
 * @author <a href="http://www.b-tu.de">BTU</a> DBIS
 *
 */
public class TopicInstanceAssociationToDao extends OpenInfraValueValueDao<
	TopicInstanceAssociationToPojo,
	TopicInstanceXTopicInstance,
	TopicInstance, TopicInstance> {

	/**
	 * This is the required constructor which calls the super constructor and
	 * in turn creates the corresponding entity manager.
	 *
	 * @param currentProjectId the current project id (this should be null when
	 *                         the system schema is selected)
	 * @param schema           the required schema
	 */
	public TopicInstanceAssociationToDao(
			UUID currentProjectId,
			OpenInfraSchemas schema) {
		super(
				currentProjectId,
				schema,
				TopicInstanceXTopicInstance.class,
				TopicInstance.class, TopicInstance.class);
	}

	@Override
	public TopicInstanceAssociationToPojo mapToPojo(
			Locale locale,
			TopicInstanceXTopicInstance txt) {
        if (txt != null) {
            TopicInstanceAssociationToPojo pojo =
                    new TopicInstanceAssociationToPojo(txt);
            pojo.setAssociationInstanceId(txt.getTopicInstance1Bean().getId());
            pojo.setRelationshipType(
                    new RelationshipTypeDao(currentProjectId, schema).mapToPojo(
                            locale,
                            txt.getRelationshipType()));
            pojo.setAssociatedInstance(
                    new TopicInstanceDao(currentProjectId, schema).mapToPojo(
                            locale,
                            txt.getTopicInstance2Bean()));
            return pojo;
        } else {
            return null;
        }
    }

    public List<TopicInstanceAssociationToPojo> readAssociationToByTopchar(
    		Locale locale, UUID topicInstance, UUID topChar,
    		int offset, int size) {
    	return readAssociation(locale, topicInstance, topChar,
    			"TopicInstanceXTopicInstance"
    			+ ".findAssociationToByTopicInstanceAndTopicCharacteristic",
    			offset, size);
    }

    public List<TopicInstanceAssociationToPojo> readAssociation(
    		Locale locale, UUID topicInstance, UUID topChar, String queryName,
    		int offset, int size) {
    	List<TopicInstanceXTopicInstance> tixtiList = em.createNamedQuery(
    			queryName, TopicInstanceXTopicInstance.class)
    			.setParameter("topicInstance",
    					em.find(TopicInstance.class, topicInstance))
    			.setParameter("topicCharacteristic",
    					em.find(TopicCharacteristic.class, topChar))
    			.setFirstResult(offset).setMaxResults(size).getResultList();
    	List<TopicInstanceAssociationToPojo> pojoList =
    			new LinkedList<TopicInstanceAssociationToPojo>();
    	for(TopicInstanceXTopicInstance tixti : tixtiList) {
    		pojoList.add(mapToPojo(locale, tixti));
    	}
    	return pojoList;
    }

	@Override
	public MappingResult<TopicInstanceXTopicInstance> mapToModel(
			TopicInstanceAssociationToPojo pojo,
			TopicInstanceXTopicInstance txt) {

	    // set relationship type
        try {
            txt.setRelationshipType(em.find(
                    RelationshipType.class,
                    pojo.getRelationshipType().getUuid()));
        } catch (NullPointerException npe) {
            throw new OpenInfraEntityException(
                    OpenInfraExceptionTypes.MISSING_DATA_IN_POJO);
        }

        // set association instance
        try {
            txt.setTopicInstance1Bean(em.find(
                    TopicInstance.class,
                    pojo.getAssociationInstanceId()));
        } catch (NullPointerException npe) {
            throw new OpenInfraEntityException(
                    OpenInfraExceptionTypes.MISSING_DATA_IN_POJO);
        }

        // set associated instance
        try {
            txt.setTopicInstance2Bean(em.find(
                    TopicInstance.class,
                    pojo.getAssociatedInstance().getUuid()));
        } catch (NullPointerException npe) {
            throw new OpenInfraEntityException(
                    OpenInfraExceptionTypes.MISSING_DATA_IN_POJO);
        }

        // return the model as mapping result
        return new MappingResult<TopicInstanceXTopicInstance>(
                txt.getId(), txt);
	}

}