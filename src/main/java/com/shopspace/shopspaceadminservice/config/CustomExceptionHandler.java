package com.shopspace.shopspaceadminservice.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	public static String getMicroserviceName(String url) {
		String contextPath = UriComponentsBuilder.fromUriString(url).build().getPath();
		int index = Objects.requireNonNull(contextPath).indexOf("/", 1);

		if (index == -1) {
			return contextPath;
		}

		return contextPath.substring(1, index);
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ResponseDTO> FeignException(FeignException feignException) throws IOException {
		String serviceName = getMicroserviceName(feignException.request().url());
		switch (feignException.status()) {
			case 400 -> {
				return ResponseUtil.badRequest(feignException.getMessage());
			}

			case 404 -> {
				return ResponseUtil.notContent(feignException.getMessage());
			}

			case 409 -> {
				return ResponseUtil.conflict(feignException.getMessage());
			}

			case 503 -> {
				return ResponseUtil.clientUnavailable(feignException.getMessage(), serviceName);
			}

			default -> {
				Map<String, Object> resp = null;
				var body = feignException.responseBody();
				if (body.isPresent()) {
					byte[] response = body.get().array();
					resp = new ObjectMapper().readValue(response, new TypeReference<>() {
					});
				}
				return ResponseUtil.badRequest(resp);
			}
		}
	}
}
