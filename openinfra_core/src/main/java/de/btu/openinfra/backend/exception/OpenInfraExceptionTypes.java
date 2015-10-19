package de.btu.openinfra.backend.exception;

/**
 * These enumeration define the OpenInfRA exception types and their
 * corresponding error messages.
 *
 * @author <a href="http://www.b-tu.de">BTU</a> DBIS
 *
 */
public enum OpenInfraExceptionTypes {

	INTERNAL_SERVER_EXCEPTION(""),

    CREATE_SCHEMA("Failed to create the new project schema."),

    RENAME_SCHEMA("Failed to rename the project schema."),

    INSERT_META_DATA("Failed to create an entry in the table "
    		+ "database_connection."),

    INSERT_BASIC_PROJECT_DATA("Failed to insert the initial project "
    		+ "information into the projects table."),

    MERGE_SYSTEM("Failed to merge the content of the system database into the"
            + " project schema."),

    INCOMPATIBLE_UUIDS("UUIDs are incompatible (URI vs Entity)."),

    NO_TRID_PASSED("Entity requires trid field."),

    ENTITY_NOT_FOUND("The requested entitiy was not found."),

    ENTITY_EXPIRED("The entity is expired."),

    MISSING_NAME_IN_POJO("The name of the object has not been set."),

    MISSING_DESCRIPTION_IN_POJO("The description of the object has not been "
            + "set."),

    MISSING_GEOM_IN_POJO("The geometry value must not be empty."),

    MISSING_VALUE_IN_POJO("The attribute value must not be empty."),

    MISSING_DATA_IN_POJO("Necessary information of the object has not been "
            + "set");

    private String msg;

    private OpenInfraExceptionTypes(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return this.msg;
    }
}
