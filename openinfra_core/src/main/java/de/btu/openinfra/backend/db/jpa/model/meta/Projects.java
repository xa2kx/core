package de.btu.openinfra.backend.db.jpa.model.meta;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import de.btu.openinfra.backend.db.jpa.model.OpenInfraModelObject;


/**
 * The persistent class for the projects database table.
 * 
 */
@Entity
@Table(schema="meta_data")
@NamedQueries({
	@NamedQuery(name="Projects.findAll", query="SELECT p FROM Projects p"),
    @NamedQuery(name="Projects.count",
    	query="SELECT COUNT(p) FROM Projects p")
})
public class Projects implements Serializable, OpenInfraModelObject {
	private static final long serialVersionUID = 1L;

	@Id
	private UUID id;

	@Column(name="is_subproject")
	private Boolean isSubproject;

	//bi-directional many-to-one association to DatabaseConnection
	@ManyToOne
	@JoinColumn(name="database_connection_id")
	private DatabaseConnection databaseConnection;
    
	//bi-directional many-to-one association to Settings
	@ManyToOne
	@JoinColumn(name="settings")
	private Settings setting;

	public Projects() {
	}

	@Override
	public UUID getId() {
		return this.id;
	}

	@Override
	public void setId(UUID id) {
		this.id = id;
	}

	public Boolean getIsSubproject() {
		return this.isSubproject;
	}

	public void setIsSubproject(Boolean isSubproject) {
		this.isSubproject = isSubproject;
	}

	public DatabaseConnection getDatabaseConnection() {
		return this.databaseConnection;
	}

	public void setDatabaseConnection(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public Settings getSetting() {
		return this.setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
	}

}