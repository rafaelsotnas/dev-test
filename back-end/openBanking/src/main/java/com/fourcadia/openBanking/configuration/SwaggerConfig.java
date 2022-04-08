package com.fourcadia.openBanking.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Open Banking")
					.description("Open Banking - 4CADIA")
					.version("v0.0.1")
				.license(new License()
					.name("GitHub")
					.url("https://github.com/rafaelsotnas"))
				.contact(new Contact()
					.name("E-mail")
					.email("rafaelferreira.0096@gmail.com")));
	}

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!"));
			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);
	}	
}
