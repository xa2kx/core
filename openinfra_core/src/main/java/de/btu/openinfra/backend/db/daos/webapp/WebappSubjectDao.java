package de.btu.openinfra.backend.db.daos.webapp;

import java.util.Locale;
import java.util.UUID;

import javax.persistence.NoResultException;

import de.btu.openinfra.backend.db.MappingResult;
import de.btu.openinfra.backend.db.OpenInfraSchemas;
import de.btu.openinfra.backend.db.daos.OpenInfraDao;
import de.btu.openinfra.backend.db.jpa.model.webapp.Webapp;
import de.btu.openinfra.backend.db.jpa.model.webapp.WebappSubject;
import de.btu.openinfra.backend.db.pojos.webapp.WebappSubjectPojo;

public class WebappSubjectDao
	extends OpenInfraDao<WebappSubjectPojo, WebappSubject> {

	public WebappSubjectDao() {
		super(null, OpenInfraSchemas.WEBAPP, WebappSubject.class);
	}

	public WebappSubjectPojo read(UUID webappId, UUID subjectId) {
		try {
			return mapToPojo(null, em.createNamedQuery(
					"WebappSubject.findByWebappAndSubject", WebappSubject.class)
					.setParameter("webapp", em.find(Webapp.class, webappId))
					.setParameter("subjectId", subjectId)
					.getSingleResult());
		} catch(NoResultException ex) {
			return null;
		}
	}

	@Override
	public WebappSubjectPojo mapToPojo(
			Locale locale, WebappSubject modelObject) {
		WebappSubjectPojo pojo = new WebappSubjectPojo(modelObject);
		pojo.setData(modelObject.getData());
		pojo.setSubject(modelObject.getSubjectId());
		pojo.setWebapp(modelObject.getWebapp().getId());
		return pojo;
	}

	@Override
	public MappingResult<WebappSubject> mapToModel(
			WebappSubjectPojo pojoObject, WebappSubject modelObject) {
		modelObject.setData(pojoObject.getData());
		modelObject.setSubjectId(pojoObject.getSubject());
		if(modelObject.getWebapp() == null) {
			modelObject.setWebapp(
					em.find(Webapp.class, pojoObject.getWebapp()));
		}
		return new MappingResult<WebappSubject>(
				modelObject.getId(), modelObject);
	}

}
