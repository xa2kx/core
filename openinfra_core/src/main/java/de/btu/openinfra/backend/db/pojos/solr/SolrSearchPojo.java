package de.btu.openinfra.backend.db.pojos.solr;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import de.btu.openinfra.backend.db.pojos.OpenInfraPojo;

/**
 * This POJO is a container for the search request from the client. It contains
 * either a simple query in Solr syntax (input from simple search) or a list of
 * complex query parts (input from extended search). The lists of projects and
 * topic characteristics will be used to filter the results.
 *
 * TODO The POJOs must extend OpenInfraPojo to be accessible for the primer
 *      class. The UUID and TRID that is provided by the OpenInfraPojo will
 *      never be used.
 *
 * @author <a href="http://www.b-tu.de">BTU</a> DBIS
 *
 */
@XmlRootElement
public class SolrSearchPojo extends OpenInfraPojo {

    /*
     * Contains a raw Solr query in Solr syntax.
     */
    private String rawSolrQuery;

    /*
     * This is a list of complex query parts that will be parsed into Solr
     * syntax.
     */
    private List<SolrComplexQueryPartPojo> complexQueryPart;

    /*
     * This project id defines a filter for the results.
     */
    private UUID projectId;

    /*
     * This topic characteristics id defines a filter for the results.
     */
    private UUID topicCharacteristicId;

    public String getRawSolrQuery() {
        return rawSolrQuery;
    }

    public void setRawSolrQuery(String rawSolrQuery) {
        this.rawSolrQuery = rawSolrQuery;
    }

    public List<SolrComplexQueryPartPojo> getComplexQueryPart() {
        return complexQueryPart;
    }

    public void setComplexQueryPart(
            List<SolrComplexQueryPartPojo> complexSolrQuery) {
        this.complexQueryPart = complexSolrQuery;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public UUID getTopicCharacteristicId() {
        return topicCharacteristicId;
    }

    public void setTopicCharacteristicId(UUID topicCharacteristicId) {
        this.topicCharacteristicId = topicCharacteristicId;
    }
}
