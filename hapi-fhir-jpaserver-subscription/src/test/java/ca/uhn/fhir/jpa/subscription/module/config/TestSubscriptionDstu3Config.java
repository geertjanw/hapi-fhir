package ca.uhn.fhir.jpa.subscription.module.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.api.IDaoRegistry;
import ca.uhn.fhir.jpa.model.sched.ISchedulerService;
import ca.uhn.fhir.jpa.searchparam.registry.ISearchParamProvider;
import ca.uhn.fhir.jpa.searchparam.registry.ISearchParamRegistry;
import ca.uhn.fhir.jpa.searchparam.registry.SearchParamRegistryImpl;
import ca.uhn.fhir.jpa.subscription.module.cache.ISubscriptionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class TestSubscriptionDstu3Config extends TestSubscriptionConfig {

	@Bean
	public FhirContext fhirContext() {
		return FhirContext.forDstu3();
	}

	@Bean
	@Primary
	public ISearchParamProvider searchParamProvider() {
		return new MockFhirClientSearchParamProvider();
	}

	@Bean
	@Primary
	public ISubscriptionProvider subscriptionProvider() {
		return new MockFhirClientSubscriptionProvider();
	}

	@Bean
	public IDaoRegistry daoRegistry() {
		IDaoRegistry retVal = mock(IDaoRegistry.class);
		when(retVal.isResourceTypeSupported(any())).thenReturn(true);
		return retVal;
	}
	
	@Bean
	public ISchedulerService schedulerService() {
		return mock(ISchedulerService.class);
	}

}
