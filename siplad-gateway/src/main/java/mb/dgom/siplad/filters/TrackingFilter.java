package mb.dgom.siplad.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


public class TrackingFilter extends ZuulFilter{

	private static final Logger log = LoggerFactory.getLogger(TrackingFilter.class);
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER=true;
	
	@Autowired
	FilterUtils filterUtils;
	
	@Override
	public boolean shouldFilter() {
		log.info(">>>>>> Should Filter - " + SHOULD_FILTER);
		return SHOULD_FILTER;
	}

	@Override
	public String filterType() {
		log.info(">>>>>> Filter Type - " + FilterUtils.PRE_FILTER_TYPE);
		return FilterUtils.PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		log.info(">>>>>> Filter Order - " + FILTER_ORDER);
		return FILTER_ORDER;
	}

	private boolean isCorrelationIdPresent(){
		if (filterUtils.getCorrelationId() !=null){
			return true;
		}
		return false;
	}
	
	private String generateCorrelationId(){
		return java.util.UUID.randomUUID().toString();
		}
	
	@Override
	public Object run() throws ZuulException {
		log.info(">>>>>> Run");
		if (isCorrelationIdPresent()) {
			log.debug("tmx-correlation-id found in tracking filter: {}." , filterUtils.getCorrelationId());
		}
		else{
			filterUtils.setCorrelationId(generateCorrelationId());
			log.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
		}
	
		RequestContext ctx = RequestContext.getCurrentContext();
		log.debug("Processing incoming request for {}.",
		ctx.getRequest().getRequestURI());
		return null;
				
	}
	
}
