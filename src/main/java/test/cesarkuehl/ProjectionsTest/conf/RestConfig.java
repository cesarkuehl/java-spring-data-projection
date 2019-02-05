package test.cesarkuehl.ProjectionsTest.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import test.cesarkuehl.ProjectionsTest.projection.CustomEmployee;

@SuppressWarnings("deprecation")
@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {
  
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.getProjectionConfiguration().addProjection(CustomEmployee.class);
    }
}
