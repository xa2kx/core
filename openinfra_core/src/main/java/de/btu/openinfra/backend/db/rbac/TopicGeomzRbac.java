package de.btu.openinfra.backend.db.rbac;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import de.btu.openinfra.backend.db.OpenInfraSchemas;
import de.btu.openinfra.backend.db.daos.AttributeValueGeomType;
import de.btu.openinfra.backend.db.daos.TopicGeomzDao;
import de.btu.openinfra.backend.db.pojos.TopicGeomzPojo;

public class TopicGeomzRbac {
	
    /**
     * The UUID of the current project.
     */
    private UUID currentProjectId;
    /**
     * The currently used schema.
     */
    private OpenInfraSchemas schema;
    /**
     * The used 3D geometry type.
     */
    private AttributeValueGeomType geomType;
    
    /**
     * The default constructor.
     */
    public TopicGeomzRbac(UUID currentProjectId, OpenInfraSchemas schema,
            AttributeValueGeomType geomType) {
        this.currentProjectId = currentProjectId;
        this.schema = schema;
        this.geomType = geomType;
    }
	
    public List<TopicGeomzPojo> read(
            Locale locale,
            UUID topicCharacteristicId,
            int offset,
            int size) {
		// Since this Class is not a rbac class, we use a closely related class 
		// to check the permission.
		new TopicInstanceRbac(
				currentProjectId, schema).checkPermission();
		return new TopicGeomzDao(
				topicCharacteristicId, 
				schema, 
				geomType).read(locale, topicCharacteristicId, offset, size);
    }
    
    public Long getCount(UUID topicCharacteristicId) {
		// Since this Class is not a rbac class, we use a closely related class 
		// to check the permission.
		new TopicInstanceRbac(
				currentProjectId, schema).checkPermission();    	
		return new TopicGeomzDao(
				topicCharacteristicId, 
				schema, 
				geomType).getCount(topicCharacteristicId);
    }

}
