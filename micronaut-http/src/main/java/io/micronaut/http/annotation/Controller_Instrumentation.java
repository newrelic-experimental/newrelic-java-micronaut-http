package io.micronaut.http.annotation;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.WeaveIntoAllMethods;
import com.newrelic.api.agent.weaver.WeaveWithAnnotation;
import com.newrelic.api.agent.weaver.Weaver;

@WeaveWithAnnotation(annotationClasses = {"io.micronaut.http.annotation.Controller"},type = MatchType.Interface)
public class Controller_Instrumentation {

	@WeaveWithAnnotation(annotationClasses = {"io.micronaut.http.annotation.Get", "io.micronaut.http.annotation.Post", "io.micronaut.http.annotation.Delete",
			"io.micronaut.http.annotation.Put", "io.micronaut.http.annotation.Head", "io.micronaut.http.annotation.Trace", "io.micronaut.http.annotation.Patch"})
	@WeaveIntoAllMethods
	@Trace
	private static void instrumentation() {
		Controller controller = Weaver.getClassAnnotation(Controller.class);
		if(controller != null) {
			String controllerValue = controller.value();
			String methodName = "Unknown";
			Get get = Weaver.getMethodAnnotation(Get.class);

			String value = null;

			if(get != null) {
				methodName = "GET";
				value = get.value();
				if(value == null) {
					value = get.uri();
					if(value == null) {
						String[] values = get.uris();
						if(values != null) {
							value = String.join(",", values);
						}
					}
				}
			} else {
				Post post = Weaver.getMethodAnnotation(Post.class);
				if(post != null) {
					methodName = "POST";
					value = post.value();
					if(value == null) {
						value = post.uri();
						if(value == null) {
							String[] values = post.uris();
							if(values != null) {
								value = String.join(",", values);
							}
						}
					}
				} else {
					Put put = Weaver.getMethodAnnotation(Put.class);
					if(put != null) {
						methodName = "PUT";
						value = put.value();
						if(value == null) {
							value = put.uri();
							if(value == null) {
								String[] values = put.uris();
								if(values != null) {
									value = String.join(",", values);
								}
							}
						}
					} else {
						Delete delete = Weaver.getMethodAnnotation(Delete.class);
						if(delete != null) {
							methodName = "DELETE";
							value = delete.value();
							if(value == null) {
								value = delete.uri();
								if(value == null) {
									String[] values = delete.uris();
									if(values != null) {
										value = String.join(",", values);
									}
								}
							}
						} else {
							Patch patch = Weaver.getMethodAnnotation(Patch.class);
							if(patch != null) {
								methodName = "PATCH";
								value = patch.value();
								if(value == null) {
									value = patch.uri();
									if(value == null) {
										String[] values = patch.uris();
										if(values != null) {
											value = String.join(",", values);
										}
									}
								}
							} else {

							}
						}
					}
				}
			}

			StringBuffer sb = new StringBuffer(methodName);
			sb.append(" - ");
			if(controllerValue != null) {
				sb.append(controllerValue);
			}
			if(value != null) {
				sb.append(value);
			}
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "MicronautController", sb.toString());
		}
	}
}
