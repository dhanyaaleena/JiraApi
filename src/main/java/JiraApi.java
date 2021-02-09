import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
//import com.atlassian.jira.rest.client.api.domain.IssueFieldId;
//import com.atlassian.jira.rest.client.api.domain.IssueType;
//import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
//import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;

public class JiraApi {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Oracle\\OpenJDK 11.0.1\\lib\\security\\cacerts");

        URI jiraServerUri = URI.create("https://team-1608787680141.atlassian.net/");

        AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();

        AuthenticationHandler auth = new BasicHttpAuthenticationHandler("dhanyaaleena@gmail.com", "MOKqqssEPk9Z8Z9xbjaLACA8");
        JiraRestClient restClient = factory.create(jiraServerUri, auth);
        IssueRestClient issueClient = restClient.getIssueClient();

        try {
            IssueInputBuilder iib = new IssueInputBuilder();
            iib.setProjectKey("JD");
            iib.setSummary("Pay orders offline");
            iib.setIssueTypeId(10001L);
            iib.setDescription("Story created using java");
            iib.setPriorityId(3L);
            iib.setAssigneeName("dhanya");
            

            IssueInput issue = iib.build();
            BasicIssue issueObj = issueClient.createIssue(issue).claim();

            System.out.println("Issue " + issueObj.getKey() + " created successfully");
        } finally {
            restClient.close();
        }
        System.exit(0);

    }
} 
//10001L story
//10004L bug
//10002L task
